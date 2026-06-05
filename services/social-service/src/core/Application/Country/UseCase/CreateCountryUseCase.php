<?php

namespace Core\Application\Country\UseCase;

use Core\Application\Country\DTO\CountryDto;

interface CreateCountryUseCase
{
    public function execute(CountryDto $countryDto): CountryDto;
}
