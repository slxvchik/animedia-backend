<?php

namespace Core\Domain\Country\Entity;

use Core\Domain\Country\Exception\InvalidIsoCodeException;

class Country
{
    public function __construct(
        private readonly string $countryIsoCode,
        private string $name
    ) {
        $this->assertCountryIsoCode($this->countryIsoCode);
    }

    private function assertCountryIsoCode(string $countryIsoCode): void
    {
        $upperIsoCode = strtoupper($countryIsoCode);
        $cleanIsoCode = preg_replace('/[^A-Z]/', '', $upperIsoCode);
        if (mb_strlen($cleanIsoCode) !== 2)
            throw new InvalidIsoCodeException($countryIsoCode);
    }

    public function getCountryIsoCode(): string
    {
        return $this->countryIsoCode;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function setName(string $name): void
    {
        $this->name = $name;
    }
}
