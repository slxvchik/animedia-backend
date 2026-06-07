<?php

declare(strict_types=1);

namespace Core\Application\Country\Mapper;

use Core\Application\Country\DTO\CountryDto;
use Core\Application\Country\DTO\CountryPublicResponseDto;
use Core\Domain\Country\Entity\Country;

interface CountryApplicationMapperInterface
{
    public function toCountry(CountryDto $countryDto): Country;
    public function toPrivateCountryDto(Country $country): CountryDto;
    public function toPublicCountryResponseDto(Country $country): CountryPublicResponseDto;
}
