<?php

declare(strict_types=1);

namespace Core\Domain\Language\Repository;

use Core\Domain\Language\Entity\Language;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface LanguageQueryRepositoryInterface
{
    public function findByCode(string $languageIsoCode): ?Language;
    /**
     * @return Page<Language>
     */
    public function search(Pageable $pageable, ?bool $active, ?string $languageIsoCode = null, ?string $name = null): Page;
    public function existsByIsoCode(string $languageIsoCode): bool;
}
