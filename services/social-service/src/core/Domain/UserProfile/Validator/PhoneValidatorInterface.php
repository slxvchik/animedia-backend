<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Validator;

use Core\Domain\PhoneCode\Entity\PhoneCode;

interface PhoneValidatorInterface
{
    public function isValid(PhoneCode $phoneCode, string $phoneNumber): bool;
}
