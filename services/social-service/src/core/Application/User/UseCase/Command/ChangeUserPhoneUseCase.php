<?php

namespace Core\Application\User\UseCase\Command;

use Core\Application\User\DTO\PhoneNumberCommandDto;

interface ChangeUserPhoneUseCase
{
    public function execute(string $userUuid, ?PhoneNumberCommandDto $newPhone): void;
}
