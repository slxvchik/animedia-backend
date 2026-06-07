<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Public;

use Core\Application\Language\DTO\Public\LanguageResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchLanguageUseCase
{
    /**
     * @return Page<LanguageResponseDto>
     */
    public function execute(?string $languageIsoCode, ?string $name, Pageable $pageable): Page;
}
