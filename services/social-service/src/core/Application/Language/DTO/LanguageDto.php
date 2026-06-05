<?php

namespace Core\Application\Language\DTO;

final readonly class LanguageDto
{
    public function __construct(
        public string $languageIsoCode,
        public string $name,
        public bool $isActive
    ) {}
}
