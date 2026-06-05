<?php

namespace Core\Application\Language\DTO;

final readonly class PublicLanguageDto
{
    public function __construct(
        public string $languageIsoCode,
        public string $name
    ) {}
}
