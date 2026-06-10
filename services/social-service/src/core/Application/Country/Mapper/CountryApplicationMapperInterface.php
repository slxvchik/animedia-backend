<?php

declare(strict_types=1);

namespace Core\Application\Country\Mapper;

use Core\Application\Country\DTO\CountryCommandDto;
use Core\Application\Country\DTO\CountryResponseDto;
use Core\Domain\Country\Entity\Country;

interface CountryApplicationMapperInterface
{
    public function toCountry(CountryCommandDto $countryDto): Country;
    public function toCountryResponseDto(?Country $country): ?CountryResponseDto;
}
