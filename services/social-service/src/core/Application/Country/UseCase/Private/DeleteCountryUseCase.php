<?php

namespace Core\Application\Country\UseCase\Private;

interface DeleteCountryUseCase
{
    public function execute(string $countryIsoCode): void;
}
