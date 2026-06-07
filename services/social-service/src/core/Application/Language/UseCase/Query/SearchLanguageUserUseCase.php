<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Query;

use Core\Application\Language\DTO\LanguagePublicResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchLanguageUserUseCase
{
    /**
     * @return Page<LanguagePublicResponseDto>
     */
    public function execute(?string $languageIsoCode, ?string $name, Pageable $pageable): Page;
}
