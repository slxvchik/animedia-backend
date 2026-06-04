<?php

namespace Core\Domain\UserProfile\ValueObject;

use Core\Domain\PhoneCode\Entity\PhoneCode;

readonly class PhoneNumber
{
    public function __construct(
        public PhoneCode $code,
        public string $number,
        public bool $confirmed = false
    ) {}
}
