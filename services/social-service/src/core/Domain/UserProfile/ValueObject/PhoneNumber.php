<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\ValueObject;

use Core\Domain\UserProfile\Exception\InvalidPhoneNumberException;
use Core\Domain\UserProfile\Service\PhoneValidatorInterface;

readonly class PhoneNumber
{
    private function __construct(
        public string $code,
        public string $number
    ) {}

    public static function create(
        ?string $code,
        ?string $number,
        PhoneValidatorInterface $validator
    ): ?self {
        if ($code === null || $number === null || trim($number) === '') {
            return null;
        }

        $cleanedNumber = trim($number);

        if (!$validator->isValid($code, $cleanedNumber)) {
            throw new InvalidPhoneNumberException($cleanedNumber);
        }

        return new self($code, $cleanedNumber, false);
    }
}
