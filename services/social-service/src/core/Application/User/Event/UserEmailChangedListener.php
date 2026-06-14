<?php

namespace Core\Application\User\Event;

use Core\Application\User\UseCase\Command\SendUserEmailConfirmMailUseCase;
use Core\Domain\User\Service\EmailTokenGenerator;
use Core\Domain\User\ValueObject\UserEmail;

final readonly class UserEmailChangedListener
{
    public function __construct(
        private SendUserEmailConfirmMailUseCase $sendUserEmailConfirmMailUseCase,
        private EmailTokenGenerator $emailTokenGenerator
    ) {}

    public function execute(UserEmail $userEmail): void
    {
        $token = $this->emailTokenGenerator->generate($userEmail);
        $this->sendUserEmailConfirmMailUseCase->execute(
            userUuid: $userEmail->userUuid,
            email: $userEmail->email,
            generatedToken: $token
        );
    }
}
