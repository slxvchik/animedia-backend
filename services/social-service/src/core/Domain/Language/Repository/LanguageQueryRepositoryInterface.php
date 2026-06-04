<?php

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
    public function search(?string $name, ?bool $isActive, Pageable $pageable): Page;
}
