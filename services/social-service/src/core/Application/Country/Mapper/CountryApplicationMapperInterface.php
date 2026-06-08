<?php

declare(strict_types=1);

namespace Core\Application\Country\Mapper;

use Core\Application\Country\DTO\CountryDto;
use Core\Domain\Country\Entity\Country;

interface CountryApplicationMapperInterface
{
    public function toCountry(CountryDto $countryDto): Country;
    public function toCountryDto(Country $country): CountryDto;
}
