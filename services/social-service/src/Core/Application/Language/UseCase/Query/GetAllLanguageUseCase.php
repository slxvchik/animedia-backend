<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Query;

use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

interface GetAllLanguageUseCase
{
    /**
     * @return Page<LanguageResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
