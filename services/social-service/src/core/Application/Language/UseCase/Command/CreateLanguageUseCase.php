<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Command;

use Core\Application\Language\DTO\LanguageDto;

interface CreateLanguageUseCase
{
    public function execute(LanguageDto $languageDto): LanguageDto;
}
