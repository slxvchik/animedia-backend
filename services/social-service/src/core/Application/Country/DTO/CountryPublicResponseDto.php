<?php

declare(strict_types=1);

namespace Core\Application\Country\DTO;

final readonly class CountryPublicResponseDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $name
    ) {}
}
