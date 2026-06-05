<?php

namespace Core\Application\Country\Mapper;

use Core\Application\Country\DTO\Private\CountryDto;
use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Domain\Country\Entity\Country;

final readonly class CountryApplicationMapper
{
    public function toCountry(CountryDto $countryDto): Country
    {
        return new Country(
            countryIsoCode: $countryDto->countryIsoCode,
            name: $countryDto->name,
            active: $countryDto->active
        );
    }

    public function toCountryDto(Country $country): CountryDto
    {
        return new CountryDto(
            countryIsoCode: $country->getCountryIsoCode(),
            name: $country->name,
            active: $country->active
        );
    }

    public function toCountryResponseDto(Country $country): CountryResponseDto
    {
        return new CountryResponseDto(
            countryIsoCode: $country->getCountryIsoCode(),
            name: $country->name
        );
    }
}
