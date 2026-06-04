<?php

namespace Core\Domain\Language\Entity;

use Core\Domain\Language\Exception\InvalidLanguageIsoCodeException;

class Language
{
    public function __construct(
        public readonly string $languageIsoCode,
        public string $name,
        public bool $isActive = false
    ) {
        $this->assertLanguageIsoCode($languageIsoCode);
    }

    private function assertLanguageIsoCode(string $languageIsoCode): void
    {
        if (!preg_match('/^[a-z]{2}$/', $languageIsoCode))
            throw new InvalidLanguageIsoCodeException($languageIsoCode);
    }
}
