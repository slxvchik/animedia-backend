<?php

namespace Core\Application\User\Phone\SendConfirm;

interface SendUserPhoneConfirmSmsUseCase
{
    public function execute(string $userUuid): void;
}
