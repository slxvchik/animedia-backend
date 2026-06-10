<?php

declare(strict_types=1);

namespace Core\Application\Country\Mapper;

use Core\Application\Country\DTO\CountryCommandDto;
use Core\Application\Country\DTO\CountryResponseDto;
use Core\Domain\Country\Entity\Country;

final readonly class CountryApplicationMapper implements CountryApplicationMapperInterface
{
    #[\Override]
    public function toCountry(CountryCommandDto $countryDto): Country
    {
        return new Country(
            isoCode: $countryDto->countryIsoCode,
            name: $countryDto->name,
            active: $countryDto->active
        );
    }

    #[\Override]
    public function toCountryResponseDto(?Country $country): ?CountryResponseDto
    {
        if ($country === null) { return null; }
        return new CountryResponseDto(
            countryIsoCode: $country->isoCode,
            name: $country->name,
            active: $country->active
        );
    }
}
