<?php

namespace Core\Application\Language\UseCase;

use Core\Application\Language\DTO\LanguageDto;

interface UpdateLanguageUseCase
{
    public function execute(LanguageDto $languageDto): LanguageDto;
}
