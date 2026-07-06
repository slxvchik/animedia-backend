<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Command;

use Core\Application\Language\DTO\LanguageCommandDto;
use Core\Application\Language\DTO\LanguageResponseDto;

interface CreateLanguageUseCase
{
    public function execute(LanguageCommandDto $languageDto): string;
}
