<?php

declare(strict_types=1);

namespace Core\Domain\Shared\PhoneNumber;

interface PhoneValidatorInterface
{
    public function isValid(string $phoneCode, string $phoneNumber): bool;
}
