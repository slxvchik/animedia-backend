<?php

namespace Core\Domain\PhoneCode\Entity;

use Core\Domain\Country\Entity\Country;
use Core\Domain\UserProfile\Exception\InvalidPhoneNumberException;

readonly class PhoneCode
{
    public function __construct(
        public Country $country,
        public string $phoneCode,
        public bool $isActive = false
    ) {
        $this->assertPhoneCode($this->phoneCode);
    }

    private function assertPhoneCode(string $phoneCode): void
    {
        if (!preg_match('/^+[0-9]{1,4}$/', $phoneCode))
            throw new InvalidPhoneNumberException($phoneCode);
    }
}
