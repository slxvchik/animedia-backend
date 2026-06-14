<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserEmailExistsException;
use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\UseCase\Command\ChangeUserEmailUseCase;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class ChangeUserEmailService implements ChangeUserEmailUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface   $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private EventDispatcherInterface       $eventDispatcher
    ) {}

    #[\Override]
    public function execute(string $userUuid, string $email): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        $emailAlreadyExists = $this->userQueryRepository->existsByEmailExcludeUserUuid($userUuid, $email);
        if ($emailAlreadyExists) {
            throw new UserEmailExistsException($email);
        }

        $user->updateEmail(
            newEmail: $email
        );

        $this->userCommandRepository->update($user);

        foreach ($user->releaseEvents() as $event) {
            $this->eventDispatcher->dispatch($event);
        }
    }
}
