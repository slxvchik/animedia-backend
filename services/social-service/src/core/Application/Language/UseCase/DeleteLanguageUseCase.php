<?php

namespace Core\Application\Language\UseCase;

interface DeleteLanguageUseCase
{
    public function execute(string $languageIsoCode): void;
}
