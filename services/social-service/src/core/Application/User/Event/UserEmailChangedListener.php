<?php

namespace Core\Application\User\Event;

use Core\Application\User\UseCase\Command\SendUserEmailConfirmMailUseCase;
use Core\Domain\User\ValueObject\UserEmail;

final readonly class UserEmailChangedListener
{
    public function __construct(
        private SendUserEmailConfirmMailUseCase $sendUserEmailConfirmMailUseCase
    ) {}

    public function execute(UserEmail $userEmail): void
    {
        $this->sendUserEmailConfirmMailUseCase->execute(
            userUuid: $userEmail->userUuid
        );
    }
}
