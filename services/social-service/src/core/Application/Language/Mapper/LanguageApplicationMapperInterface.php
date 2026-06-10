<?php

declare(strict_types=1);

namespace Core\Application\Language\Mapper;

use Core\Application\Language\DTO\LanguageCommandDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Domain\Language\Entity\Language;

interface LanguageApplicationMapperInterface
{
    public function toLanguage(LanguageCommandDto $languageDto): Language;
    public function toLanguageResponseDto(?Language $language): ?LanguageResponseDto;
}
