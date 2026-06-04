<?php

namespace Core\Domain\UserProfile\Validator;

use Core\Domain\PhoneCode\Entity\PhoneCode;

interface PhoneValidatorInterface
{
    function isValid(PhoneCode $phoneCode, string $phoneNumber): bool;
}
