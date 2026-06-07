<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Command;

interface DeleteCountryUseCase
{
    public function execute(string $countryIsoCode): void;
}
