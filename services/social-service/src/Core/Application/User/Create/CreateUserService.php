<?php

declare(strict_types=1);

namespace Core\Application\User\Create;

use Core\Application\User\Shared\Exception\UserEmailExistsException;
use Core\Application\User\Shared\Exception\UserUsernameExistsException;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\Shared\Uuid\UuidGeneratorInterface;
use Core\Domain\User\Entity\User;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class CreateUserService implements CreateUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface   $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private UuidGeneratorInterface         $uuidGenerator,
        private EventDispatcherInterface       $eventDispatcher
    ) {}

    #[\Override]
    public function execute(CreateUserCommandDto $userDto): string
    {
        $newUser = User::createNew(
            username: $userDto->username,
            usernameCode: $userDto->usernameCode,
            email: $userDto->email,
            emailConfirmed: $userDto->emailConfirmed,
            localeLanguageIsoCode: $userDto->localeLanguageIsoCode,
            uuidGenerator: $this->uuidGenerator
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
