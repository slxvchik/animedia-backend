<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Query;

use Core\Application\Language\DTO\LanguageResponseDto;

interface GetByLanguageUuidListUseCase
{
    /**
     * @param string[] $uuid
     * @return LanguageResponseDto[]
     */
    public function execute(array $uuid): array;
}
