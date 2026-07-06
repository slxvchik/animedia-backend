<?php

declare(strict_types=1);

namespace Core\Application\Language\Mapper;

use Core\Application\Language\DTO\LanguageCommandDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Domain\Language\Entity\Language;

class LanguageApplicationMapper implements LanguageApplicationMapperInterface
{
    #[\Override]
    public function toLanguage(LanguageCommandDto $languageDto): Language
    {
        return new Language(
            isoCode: $languageDto->languageIsoCode,
            name: $languageDto->name,
            active: $languageDto->isActive
        );
    }

    #[\Override]
    public function toLanguageResponseDto(?Language $language): ?LanguageResponseDto
    {
        if (null === $language) { return null; }
        return new LanguageResponseDto(
            languageIsoCode: $language->isoCode,
            name: $language->name,
            active: $language->active
        );
    }
}
