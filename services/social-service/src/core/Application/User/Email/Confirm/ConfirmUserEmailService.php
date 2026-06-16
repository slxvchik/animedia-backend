<?php

namespace Core\Application\User\ChangeEmail\Confirm;

use Core\Application\User\Email\Confirm\ConfirmUserEmailUseCase;
use Core\Application\User\Email\Shared\Exception\UserEmailConfirmException;
use Core\Application\User\Shared\Exception\UserNotFoundException;
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
