<?php

declare(strict_types=1);

namespace Core\Application\Language\Mapper;

use Core\Application\Language\DTO\LanguageDto;
use Core\Application\Language\DTO\LanguagePublicResponseDto;
use Core\Domain\Language\Entity\Language;

interface LanguageApplicationMapperInterface
{
    public function toLanguage(LanguageDto $languageDto): Language;
    public function toPrivateLanguageDto(Language $language): LanguageDto;
    public function toPublicLanguageResponseDto(Language $language): LanguagePublicResponseDto;
}
