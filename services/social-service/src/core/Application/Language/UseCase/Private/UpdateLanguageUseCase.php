<?php

namespace Core\Application\Language\UseCase\Private;

use Core\Application\Language\DTO\Private\LanguageDto;

interface UpdateLanguageUseCase
{
    public function execute(LanguageDto $languageDto): LanguageDto;
}
