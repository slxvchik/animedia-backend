<?php

declare(strict_types=1);

namespace Core\Application\User\Query\Shared\Mapper;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\User\Query\Shared\DTO\PhoneNumberResponseDto;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Domain\Shared\PhoneNumber\PhoneNumber;
use Core\Domain\User\Entity\User;

interface UserApplicationQueryMapperInterface
{
    public function toPhoneNumberResponseDto(?PhoneNumber $phoneNumber, ?PhoneCodeResponseDto $phoneCodeResponseDto): ?PhoneNumberResponseDto;

    /**
     * @param LanguageResponseDto[]|null $languageResponseDtoList
     */
    public function toUserResponseDto(?User $user, ?PhoneNumberResponseDto $phoneNumberResponseDto, ?array $languageResponseDtoList = null, ?CountryResponseDto $countryResponseDto = null): ?UserResponseDto;
}
