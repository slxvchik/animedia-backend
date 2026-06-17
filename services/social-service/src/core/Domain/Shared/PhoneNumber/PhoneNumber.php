<?php

declare(strict_types=1);

namespace Core\Domain\Shared\PhoneNumber;

use Core\Domain\User\Exception\UserInvalidPhoneNumberException;

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
    ): ?self
    {
        if ($code === null || $number === null || trim($number) === '') {
            return null;
        }

        $cleanedNumber = trim($number);

        if (!$validator->isValid($code, $cleanedNumber)) {
            throw new UserInvalidPhoneNumberException($cleanedNumber);
        }

        return new self($code, $cleanedNumber);
    }

    public static function safeEquals(?PhoneNumber $a, ?PhoneNumber $b): bool
    {
        if ($a === null && $b === null) {
            return true;
        }
        if ($a === null || $b === null) {
            return false;
        }
        return $a->code === $b->code && $a->number === $b->number;
    }
}
