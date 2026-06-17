<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\SendConfirm;

interface SendUserPhoneConfirmSmsUseCase
{
    public function execute(string $userUuid): void;
}
