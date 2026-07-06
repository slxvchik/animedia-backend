<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Query;

use Core\Application\Language\DTO\LanguageResponseDto;

interface GetByLanguageUuidUseCase
{
    public function execute(string $uuid): ?LanguageResponseDto;
}
