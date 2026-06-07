<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Query;

use Core\Application\Language\DTO\LanguageDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchLanguageAdminUseCase
{
    /**
     * @return Page<LanguageDto>
     */
    public function execute(?string $languageIsoCode, ?string $name, ?bool $isActive, Pageable $pageable): Page;
}
