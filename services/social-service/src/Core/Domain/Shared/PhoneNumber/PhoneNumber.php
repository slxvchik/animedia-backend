<?php

declare(strict_types=1);

namespace Core\Domain\Shared\PhoneNumber;

use Core\Domain\User\Exception\UserInvalidPhoneNumberException;

readonly class PhoneNumber
{
    private function __construct(
        public string $phoneCodeUuid,
        public string $phoneCode,
        public string $number
    ) {}

    public static function create(
        ?string                 $phoneCodeUuid,
        ?string                 $phoneCode,
        ?string                 $number,
        PhoneValidatorInterface $validator
    ): ?self {
        if ($phoneCodeUuid === null || $phoneCode === null || $number === null || trim($number) === '') {
            return null;
        }

        $cleanedNumber = trim($number);

        if (!$validator->isValid($phoneCode, $cleanedNumber)) {
            throw new UserInvalidPhoneNumberException($cleanedNumber);
        }

        return new self($phoneCodeUuid, $phoneCode, $cleanedNumber);
    }

    public static function safeEquals(?PhoneNumber $a, ?PhoneNumber $b): bool
    {
        if ($a === null && $b === null) {
            return true;
        }
        if ($a === null || $b === null) {
            return false;
        }
        return $a->phoneCodeUuid === $b->phoneCodeUuid && $a->number === $b->number;
    }
}
