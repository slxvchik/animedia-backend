<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Private;

use Core\Application\Language\DTO\Private\LanguageDto;

interface CreateLanguageUseCase
{
    public function execute(LanguageDto $languageDto): LanguageDto;
}
