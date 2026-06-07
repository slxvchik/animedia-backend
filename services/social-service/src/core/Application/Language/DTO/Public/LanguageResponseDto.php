<?php

declare(strict_types=1);

namespace Core\Application\Language\DTO\Public;

final readonly class LanguageResponseDto
{
    public function __construct(
        public string $languageIsoCode,
        public string $name
    ) {}
}
