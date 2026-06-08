<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Query;

use Core\Application\Country\DTO\CountryResponseDto;

interface GetCountryUseCase
{
    public function execute(string $countryIsoCode): CountryResponseDto;
}
