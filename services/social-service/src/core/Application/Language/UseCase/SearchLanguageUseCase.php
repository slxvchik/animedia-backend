<?php

namespace Core\Application\Language\UseCase;

use Core\Application\Language\DTO\LanguageDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchLanguageUseCase
{
    /**
     * @return Page<LanguageDto>
     */
    public function execute(?string $name, ?string $languageIsoCode, ?bool $isActive, Pageable $pageable): Page;
}
