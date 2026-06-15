<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserEmailConfirmException;
use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\UseCase\Command\ConfirmUserEmailUseCase;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;
use Core\Domain\User\Service\EmailTokenGenerator;

final readonly class ConfirmUserEmailService implements ConfirmUserEmailUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface   $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private EmailTokenGenerator            $emailTokenGenerator
    ) {}


    #[\Override]
    public function execute(string $token): void
    {
        $userEmail = $this->emailTokenGenerator->validate($token);

        $user = $this->userQueryRepository->findByUserUuid($userEmail->userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userEmail->userUuid);
        }

        if ($user->email === $userEmail->email) {
            $user->confirmEmail();
        } else {
            throw new UserEmailConfirmException(
                curEmail: $user->email,
                tokenEmail: $userEmail->email
            );
        }

        $this->userCommandRepository->update($user);
    }
}
