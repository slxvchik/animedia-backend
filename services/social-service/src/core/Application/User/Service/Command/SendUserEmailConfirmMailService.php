<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserConfirmEmailException;
use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\UseCase\Command\SendUserEmailConfirmMailUseCase;
use Core\Domain\Shared\Service\EmailSenderInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class SendUserEmailConfirmMailService implements SendUserEmailConfirmMailUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private EmailSenderInterface $emailSender
    ) {}

    #[\Override]
    public function execute(string $userUuid, string $email, string $generatedToken): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        if ($user->emailConfirmed) {
            return;
        }

        if ($user->email !== $email) {
            throw new UserConfirmEmailException(
                curEmail: $user->email,
                tokenEmail: $email
            );
        }

        $this->emailSender->send(
            toEmail: $email,
            template: 'email_confirmation',
            locale: $user->localeLanguageIsoCode,
            vars: ['token' => $generatedToken]
        );
    }
}
