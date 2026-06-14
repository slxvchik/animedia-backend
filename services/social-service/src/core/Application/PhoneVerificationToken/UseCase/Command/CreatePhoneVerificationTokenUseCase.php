<?php

namespace Core\Application\PhoneVerificationToken\UseCase\Command;

use Core\Domain\Shared\ValueObject\PhoneNumber;

interface CreatePhoneVerificationTokenUseCase
{
    public function execute(string $userUuid, PhoneNumber $phoneNumber): void;
}
