<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Private;

interface DeleteCountryUseCase
{
    public function execute(string $countryIsoCode): void;
}
