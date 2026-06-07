<?php

declare(strict_types=1);

namespace Core\Application\Language\DTO;

final readonly class LanguagePublicResponseDto
{
    public function __construct(
        public string $languageIsoCode,
        public string $name
    ) {}
}
