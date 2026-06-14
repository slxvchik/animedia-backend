<?php

declare(strict_types=1);

namespace Core\Domain\Country\Repository;

use Core\Domain\Country\Entity\Country;

interface CountryCommandRepositoryInterface
{
    public function create(Country $country): string;
    public function update(Country $country): void;
    public function delete(string $isoCode): void;
}
