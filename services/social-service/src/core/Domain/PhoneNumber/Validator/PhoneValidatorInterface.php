<?php

namespace Core\Domain\PhoneNumber\Validator;

interface PhoneValidatorInterface
{
    function validate(string $phoneCode, string $phoneNumber, string $countryIsoCode): bool;
}
