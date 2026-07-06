<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetUserRelatedEntities;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;

final readonly class UserRelatedEntities
{
    /**
     * @param LanguageResponseDto[] $languageDtoMap
     * @param CountryResponseDto[] $countryDtoMap
     * @param PhoneCodeResponseDto[] $phoneCodeDtoMap
     */
    public function __construct(
        public array $languageDtoMap,
        public array $countryDtoMap,
        public array $phoneCodeDtoMap
    ) {}
}
