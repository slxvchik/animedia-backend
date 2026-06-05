<?php

namespace Core\Application\Country\DTO\Private;

final readonly class CountryDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $name,
        public bool $active
    ) {}
}
