<?php

namespace Core\Application\Country\UseCase;

interface DeleteCountryUseCase
{
    public function execute(string $countryIsoCode);
}
