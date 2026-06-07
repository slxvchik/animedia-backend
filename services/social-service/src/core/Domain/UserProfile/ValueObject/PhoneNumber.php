<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\ValueObject;

use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\UserProfile\Exception\InvalidPhoneNumberException;
use Core\Domain\UserProfile\Validator\PhoneValidatorInterface;

readonly class PhoneNumber
{
    private function __construct(
        public PhoneCode $code,
        public string $number,
        public bool $confirmed = false
    ) {}

    public static function create(
        ?PhoneCode $code,
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

    public function confirm(): self
    {
        return new self($this->code, $this->number, true);
    }
}
