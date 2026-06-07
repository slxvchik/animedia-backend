<?php

declare(strict_types=1);

namespace Core\Domain\Country\Repository;

use Core\Domain\Country\Entity\Country;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface CountryQueryRepositoryInterface
{
    public function findByIsoCode(string $isoCode): ?Country;
    /**
     * @param string[] $isoCodeList
     * @return Country[]
     */
    public function findByIsoCodeList(array $isoCodeList, ?bool $active = null): array;
    /**
     * @return Page<Country>
     */
    public function search(Pageable $pageable, ?bool $active, ?string $countryIsoCode = null, ?string $name = null): Page;
    public function existsByIsoCode(string $isoCode): bool;
}
