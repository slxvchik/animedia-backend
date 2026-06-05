<?php

namespace Core\Application\Country\DTO;

final readonly class PublicCountryDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $name
    ) {}
}
