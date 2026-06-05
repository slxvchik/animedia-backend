<?php

namespace Core\Application\Country\UseCase\Private;

use Core\Application\Country\DTO\Private\CountryDto;

interface UpdateCountryUseCase
{
    public function execute(CountryDto $countryDto): CountryDto;
}
