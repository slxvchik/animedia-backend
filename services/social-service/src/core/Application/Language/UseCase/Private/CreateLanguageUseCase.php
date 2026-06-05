<?php

namespace Core\Application\Language\UseCase\Private;

use Core\Application\Language\DTO\Private\LanguageDto;

interface CreateLanguageUseCase
{
    public function execute(LanguageDto $languageDto): LanguageDto;
}
