<?php

namespace Core\Application\UserProfile\Service;

use Core\Application\UserProfile\DTO\CreateUserProfileCommandDto;
use Core\Application\UserProfile\DTO\UserProfileResponseDto;
use Core\Application\UserProfile\Mapper\UserProfileApplicationMapperInterface;
use Core\Application\UserProfile\UseCase\Command\CreateUserProfileUseCase;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\UserProfile\Entity\UserProfile;
use Core\Domain\UserProfile\Exception\EmailExistsException;
use Core\Domain\UserProfile\Exception\UsernameExistsException;
use Core\Domain\UserProfile\Repository\UserProfileCommandRepositoryInterface;
use Core\Domain\UserProfile\Repository\UserProfileQueryRepositoryInterface;
use Core\Domain\UserProfile\Service\EmailTokenGenerator;

final readonly class CreateUserProfileService implements CreateUserProfileUseCase
{
    public function __construct(
        private UserProfileQueryRepositoryInterface $userProfileQueryRepository,
        private UserProfileCommandRepositoryInterface $userProfileCommandRepository,
        private UserProfileApplicationMapperInterface $userProfileApplicationMapper,
        private IdentityGeneratorInterface $identityGenerator,
        private EmailTokenGenerator $emailTokenGenerator,
        private EventDispatcherInterface $eventDispatcher
    ) {}

    #[\Override]
    public function execute(CreateUserProfileCommandDto $userProfileDto): UserProfileResponseDto
    {
        $generatedUuid = $this->identityGenerator->generate();

        // validate fields
        $newUser = UserProfile::createNew(
            userUuid: $generatedUuid,
            username: $userProfileDto->username,
            usernameCode: $userProfileDto->usernameCode,
            email: $userProfileDto->email,
            emailConfirmed: $userProfileDto->emailConfirmed
        );

        $usernameExists = $this->userProfileQueryRepository->existsByUsernameAndUsernameCode($newUser->username, $newUser->usernameCode);
        if ($usernameExists) {
            throw new UsernameExistsException($newUser->username, $newUser->usernameCode);
        }

        $emailExists = $this->userProfileQueryRepository->existsByEmail($newUser->email);
        if ($emailExists) {
            throw new EmailExistsException($newUser->email);
        }

        if (!$newUser->emailConfirmed) {
            $emailToken = $this->emailTokenGenerator->generate($newUser->email);
            $newUser->initiateEmailConfirmation($emailToken);
        }

        // save unconfirmed entity
        $created = $this->userProfileCommandRepository->create($newUser);

        // send email confirmation
        foreach ($newUser->releaseEvents() as $event) {
            $this->eventDispatcher->dispatch($event);
        }

        return $this->userProfileApplicationMapper->toUserProfileResponseDto(
            userProfile: $created
        );
    }
}
