<?php

declare(strict_types=1);

namespace Core\Domain\Language\Repository;

use Core\Domain\Language\Entity\Language;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

interface LanguageQueryRepositoryInterface
{
    /**
     * @param Pageable $pageable
     * @return Page<Language>
     */
    public function findAll(Pageable $pageable): Page;

    public function findByIsoCode(string $isoCode): ?Language;

    /**
     * @param string[] $isoCodeList
     * @return Language[]
     */
    public function findByIsoCodeList(array $isoCodeList): array;

    public function existsByIsoCode(string $languageIsoCode): bool;
}
