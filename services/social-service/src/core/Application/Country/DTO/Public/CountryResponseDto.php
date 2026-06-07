<?php

declare(strict_types=1);

namespace Core\Application\Country\DTO\Public;

final readonly class CountryResponseDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $name
    ) {}
}
