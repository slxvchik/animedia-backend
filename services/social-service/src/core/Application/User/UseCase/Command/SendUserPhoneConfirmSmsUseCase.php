<?php

namespace Core\Application\User\UseCase\Command;

interface SendUserPhoneConfirmSmsUseCase
{
    public function execute(string $userUuid): void;
}
