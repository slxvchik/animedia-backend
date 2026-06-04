<?php

namespace Core\Domain\PhoneCode\Entity;

use Core\Domain\Country\Entity\Country;
use Core\Domain\PhoneNumber\Exception\InvalidPhoneNumberException;

class PhoneCode
{
    public function __construct(
        private Country $country,
        private string $phoneCode
    ) {
        $this->assertPhoneCode($this->phoneCode);
    }

    private function assertPhoneCode(string $phoneCode): void
    {
        if (mb_strlen(trim($phoneCode)) === 0)
            throw new InvalidPhoneNumberException($phoneCode);
    }

    public function getCountry(): Country
    {
        return $this->country;
    }

    public function setCountry(Country $country): void
    {
        $this->country = $country;
    }

    public function getPhoneCode(): string
    {
        return $this->phoneCode;
    }

    public function setPhoneCode(string $phoneCode): void
    {
        $this->phoneCode = $phoneCode;
    }
}
