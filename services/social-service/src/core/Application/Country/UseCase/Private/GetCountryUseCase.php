<?php

namespace Core\Application\Country\UseCase\Private;

use Core\Application\Country\DTO\Private\CountryDto;

interface GetCountryUseCase
{
    public function execute(string $countryIsoCode): CountryDto;
}
