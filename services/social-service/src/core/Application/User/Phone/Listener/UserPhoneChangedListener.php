<?php

namespace Core\Application\User\Phone\Listener;

use Core\Application\User\Phone\SendConfirm\SendUserPhoneConfirmSmsUseCase;

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
