<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Command;

use Core\Application\Country\DTO\CountryCommandDto;
use Core\Application\Country\DTO\CountryResponseDto;

interface CreateCountryUseCase
{
    public function execute(CountryCommandDto $countryDto): CountryResponseDto;
}
