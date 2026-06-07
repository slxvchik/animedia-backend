<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Query;

use Core\Application\Country\DTO\CountryDto;

interface GetCountryAdminUseCase
{
    public function execute(string $countryIsoCode): CountryDto;
}
