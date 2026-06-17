<?php

declare(strict_types=1);

namespace Core\Domain\Country\Repository;

use Core\Domain\Country\Entity\Country;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

interface CountryQueryRepositoryInterface
{
    /**
     * @param Pageable $pageable
     * @return Page<Country>
     */
    public function findAll(Pageable $pageable): Page;

    public function findByIsoCode(string $isoCode): ?Country;

    /**
     * @param string[] $isoCodeList
     * @return Country[]
     */
    public function findByIsoCodeList(array $isoCodeList): array;

    public function existsByIsoCode(string $isoCode): bool;
}
