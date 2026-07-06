<?php

declare(strict_types=1);

namespace Core\Domain\Language\Repository;

use Core\Domain\Language\Entity\Language;

interface LanguageCommandRepositoryInterface
{
    public function create(Language $language): string;
    public function update(Language $language): void;
    public function delete(string $languageIsoCode): void;
}
