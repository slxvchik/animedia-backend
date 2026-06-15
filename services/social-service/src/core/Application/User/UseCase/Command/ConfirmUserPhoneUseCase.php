<?php

namespace Core\Application\User\UseCase\Command;

interface ConfirmUserPhoneUseCase
{
    public function execute(string $userUuid, int $code): void;
}
