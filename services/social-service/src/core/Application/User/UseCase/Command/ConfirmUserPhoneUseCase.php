<?php

namespace Core\Application\User\UseCase\Command;

use Core\Domain\Shared\ValueObject\PhoneNumber;

interface ConfirmUserPhoneUseCase
{
    public function execute(string $userUuid, PhoneNumber $phoneNumber, int $code): void;
}
