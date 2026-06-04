<?php

namespace Core\Domain\Country\Repository;

use Core\Domain\Country\Entity\Country;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface CountryQueryRepositoryInterface
{
    public function findByIsoCode(string $isoCode): ?Country;
    /**
     * @return Page<Country>
     */
    public function search(?string $name, ?bool $isActive, Pageable $pageable): Page;
}
