<?php

namespace Core\Application\Language\UseCase;

use Core\Application\Language\DTO\LanguageDto;

interface CreateLanguageUseCase
{
    public function execute(LanguageDto $languageDto): LanguageDto;
}
