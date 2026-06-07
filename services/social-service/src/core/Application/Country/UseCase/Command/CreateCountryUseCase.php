<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Command;

use Core\Application\Country\DTO\CountryDto;

interface CreateCountryUseCase
{
    public function execute(CountryDto $countryDto): CountryDto;
}
