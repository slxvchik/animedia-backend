<?php

declare(strict_types=1);

namespace Core\Domain\Language\Repository;

use Core\Domain\Language\Entity\Language;

interface LanguageCommandRepositoryInterface
{
    public function create(Language $language): Language;
    public function update(Language $language): Language;
    public function delete(string $languageIsoCode): void;
}
