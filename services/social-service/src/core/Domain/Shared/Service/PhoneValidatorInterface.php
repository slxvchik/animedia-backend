<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Service;

interface PhoneValidatorInterface
{
    public function isValid(string $phoneCode, string $phoneNumber): bool;
}
