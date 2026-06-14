<?php

namespace Core\Application\User\Event;

use Core\Application\User\UseCase\Command\SendUserPhoneConfirmSmsUseCase;

final readonly class UserPhoneChangedListener
{
    public function __construct(
        private SendUserPhoneConfirmSmsUseCase $sendUserPhoneConfirmSmsUseCase
    ) {}

    public function execute(string $userUuid): void
    {
        $this->sendUserPhoneConfirmSmsUseCase->execute(
            userUuid: $userUuid
        );
    }
}
