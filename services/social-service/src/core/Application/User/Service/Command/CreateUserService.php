<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\DTO\CreateUserCommandDto;
use Core\Application\User\Exception\UserEmailExistsException;
use Core\Application\User\Exception\UserUsernameExistsException;
use Core\Application\User\UseCase\Command\CreateUserUseCase;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\User\Entity\User;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class CreateUserService implements CreateUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface   $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private IdentityGeneratorInterface     $identityGenerator,
        private EventDispatcherInterface       $eventDispatcher
    ) {}

    #[\Override]
    public function execute(CreateUserCommandDto $userDto): string
    {
        // validate fields
        $newUser = User::createNew(
            username: $userDto->username,
            usernameCode: $userDto->usernameCode,
            email: $userDto->email,
            emailConfirmed: $userDto->emailConfirmed,
            localeLanguageIsoCode: $userDto->localeLanguageIsoCode,
            identityGenerator: $this->identityGenerator
        );

        $usernameExists = $this->userQueryRepository->existsByUsernameAndUsernameCode($newUser->username, $newUser->usernameCode);
        if ($usernameExists) {
            throw new UserUsernameExistsException($newUser->username, $newUser->usernameCode);
        }

        $emailExists = $this->userQueryRepository->existsByEmail($newUser->email);
        if ($emailExists) {
            throw new UserEmailExistsException($newUser->email);
        }

        if (!$newUser->emailConfirmed) {
            $newUser->initiateEmailConfirmation();
        }

        // save unconfirmed entity
        $createdUuid = $this->userCommandRepository->create($newUser);

        // send email confirmation
        foreach ($newUser->releaseEvents() as $event) {
            $this->eventDispatcher->dispatch($event);
        }

        return $createdUuid;
    }
}
