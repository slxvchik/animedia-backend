<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Query;

use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetAllLanguageForIndexUseCase
{
    /**
     * @return Page<LanguageResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
