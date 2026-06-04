<?php

namespace Core\Domain\Country\Repository;

use Core\Domain\Country\Entity\Country;
use Core\Domain\Pagination\Entity\Page;
use Core\Domain\Pagination\Entity\Pageable;

interface CountryQueryRepositoryInterface
{
    public function findByIsoCode(string $isoCode): ?Country;
    /**
     * @return Page<Country>
     */
    public function search(?string $name, Pageable $pageable): Page;
}
