<?php

namespace Core\Domain\Country\Repository;

use Core\Domain\Country\Entity\Country;

interface CountryCommandRepositoryInterface
{
    public function create(Country $country): Country;
    public function update(Country $country): Country;
    public function delete(string $isoCode): void;
}
