<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserEmailConfirmException;
use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\UseCase\Command\SendUserEmailConfirmMailUseCase;
use Core\Domain\Shared\Service\EmailSenderInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;
use Core\Domain\User\Service\EmailTokenGenerator;
use Core\Domain\User\ValueObject\UserEmail;

final readonly class SendUserEmailConfirmMailService implements SendUserEmailConfirmMailUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private EmailTokenGenerator $emailTokenGenerator,
        private EmailSenderInterface $emailSender
    ) {}

    #[\Override]
    public function execute(string $userUuid): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        if ($user->emailConfirmed) {
            return;
        }

        $userEmail = new UserEmail(
            userUuid: $user->uuid,
            email: $user->email
        );

        $generatedToken = $this->emailTokenGenerator->generate(
            email: $userEmail
        );

        $this->emailSender->send(
            toEmail: $user->email,
            template: 'user_email_confirmation',
            localeLanguageIsoCode: $user->localeLanguageIsoCode,
            vars: ['token' => $generatedToken]
        );
    }
}
