<?php

declare(strict_types=1);

namespace Core\Application\Country\Mapper;

use Core\Application\Country\DTO\Private\CountryDto;
use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Domain\Country\Entity\Country;

final readonly class CountryApplicationMapper implements CountryApplicationMapperInterface
{
    #[\Override]
    public function toCountry(CountryDto $countryDto): Country
    {
        return new Country(
            isoCode: $countryDto->countryIsoCode,
            name: $countryDto->name,
            active: $countryDto->active
        );
    }

    #[\Override]
    public function toPrivateCountryDto(Country $country): CountryDto
    {
        return new CountryDto(
            countryIsoCode: $country->isoCode,
            name: $country->name,
            active: $country->active
        );
    }

    #[\Override]
    public function toPublicCountryResponseDto(Country $country): CountryResponseDto
    {
        return new CountryResponseDto(
            countryIsoCode: $country->isoCode,
            name: $country->name
        );
    }
}
