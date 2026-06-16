<?php

namespace Core\Application\User\Query\Shared\Mapper;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\User\Query\Shared\DTO\PhoneNumberResponseDto;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Domain\Shared\ValueObject\PhoneNumber;
use Core\Domain\User\Entity\User;

interface UserApplicationMapperInterface
{
    public function toPhoneNumberResponseDto(?PhoneNumber $phoneNumber): ?PhoneNumberResponseDto;

    /**
     * @param LanguageResponseDto[]|null $languageResponseDtoList
     */
    public function toUserResponseDto(?User $user, ?array $languageResponseDtoList = null, ?CountryResponseDto $countryResponseDto = null): ?UserResponseDto;
}
