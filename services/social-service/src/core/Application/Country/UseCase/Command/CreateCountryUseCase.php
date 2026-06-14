<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Command;

use Core\Application\Country\DTO\CountryCommandDto;

interface CreateCountryUseCase
{
    public function execute(CountryCommandDto $countryDto): string;
}
