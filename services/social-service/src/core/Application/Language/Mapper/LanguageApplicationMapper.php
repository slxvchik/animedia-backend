<?php

declare(strict_types=1);

namespace Core\Application\Language\Mapper;

use Core\Application\Language\DTO\LanguageDto;
use Core\Application\Language\DTO\LanguagePublicResponseDto;
use Core\Domain\Language\Entity\Language;

class LanguageApplicationMapper implements LanguageApplicationMapperInterface
{
    #[\Override]
    public function toLanguage(LanguageDto $languageDto): Language
    {
        return new Language(
            isoCode: $languageDto->languageIsoCode,
            name: $languageDto->name,
            active: $languageDto->isActive
        );
    }

    #[\Override]
    public function toPrivateLanguageDto(Language $language): LanguageDto
    {
        return new LanguageDto(
            languageIsoCode: $language->isoCode,
            name: $language->name,
            isActive: $language->active
        );
    }

    #[\Override]
    public function toPublicLanguageResponseDto(Language $language): LanguagePublicResponseDto
    {
        return new LanguagePublicResponseDto(
            languageIsoCode: $language->isoCode,
            name: $language->name
        );
    }
}
