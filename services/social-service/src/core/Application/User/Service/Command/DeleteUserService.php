<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\UseCase\Command\DeleteUserUseCase;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class DeleteUserService implements DeleteUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface   $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository
    ) {}

    #[\Override]
    public function execute(string $userUuid): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        $this->userCommandRepository->delete($user);
    }
}
