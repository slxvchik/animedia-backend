<?php

namespace Core\Domain\Country\Entity;

use Core\Domain\Country\Exception\InvalidCountryIsoCodeException;

class Country
{
    public function __construct(
        private readonly string $countryIsoCode,
        public string $name,
        public bool $active = false,
    ) {
        $this->assertCountryIsoCode($this->countryIsoCode);
    }

    private function assertCountryIsoCode(string $countryIsoCode): void
    {
        if (preg_match('/^[A-Z]{2}$/', $countryIsoCode))
            throw new InvalidCountryIsoCodeException($countryIsoCode);
    }

    public function getCountryIsoCode(): string
    {
        return $this->countryIsoCode;
    }
}
