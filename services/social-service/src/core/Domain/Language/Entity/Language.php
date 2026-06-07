<?php

declare(strict_types=1);

namespace Core\Domain\Language\Entity;

use Core\Domain\Language\Exception\InvalidLanguageIsoCodeException;

final class Language
{
    public readonly string $isoCode;
    public private(set) string $name;
    public private(set) bool $active;

    public function __construct(
        string $isoCode,
        string $name,
        bool   $active = false
    ) {
        $this->assertLanguageIsoCode($isoCode);
        $this->isoCode = $isoCode;

        $this->name = $name;
        $this->active = $active;
    }

    private function assertLanguageIsoCode(string $languageIsoCode): void
    {
        if (!preg_match('/^[a-z]{2}$/', $languageIsoCode)) {
            throw new InvalidLanguageIsoCodeException($languageIsoCode);
        }
    }

    public function update(string $name, bool $active): void
    {
        $this->name = $name;
        $this->active = $active;
    }
}
