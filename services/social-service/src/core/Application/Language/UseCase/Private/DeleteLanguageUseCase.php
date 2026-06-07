<?php

declare(strict_types=1);

namespace Core\Application\Language\UseCase\Private;

interface DeleteLanguageUseCase
{
    public function execute(string $languageIsoCode): void;
}
