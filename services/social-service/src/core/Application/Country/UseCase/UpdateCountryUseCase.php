<?php

namespace Core\Application\Country\UseCase;

use Core\Application\Country\DTO\CountryDto;

interface UpdateCountryUseCase
{
    public function execute(CountryDto $countryDto): CountryDto;
}
