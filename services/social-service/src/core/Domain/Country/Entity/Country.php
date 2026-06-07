<?php

declare(strict_types=1);

namespace Core\Domain\Country\Entity;

use Core\Domain\Country\Exception\InvalidCountryIsoCodeException;

final class Country
{
    public readonly string $isoCode;
    public private(set) string $name;
    public private(set) bool $active;

    public function __construct(
        string $isoCode,
        string $name,
        bool   $active = false
    ) {
        $this->assertCountryIsoCode($isoCode);
        $this->isoCode = $isoCode;

        $this->name = $name;
        $this->active = $active;
    }

    private function assertCountryIsoCode(string $countryIsoCode): void
    {
        if (!preg_match('/^[A-Z]{2}$/', $countryIsoCode)) {
            throw new InvalidCountryIsoCodeException($countryIsoCode);
        }
    }

    public function update(
        string $name,
        bool $active
    ): void {
        $this->name = $name;
        $this->active = $active;
    }
}
