<?php

namespace Core\Application\Language\UseCase\Private;

interface DeleteLanguageUseCase
{
    public function execute(string $languageIsoCode): void;
}
