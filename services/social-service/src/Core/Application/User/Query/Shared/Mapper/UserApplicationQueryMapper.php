<?php

namespace Core\Application\User\Query\Shared\Mapper;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\User\Query\Shared\DTO\PhoneNumberResponseDto;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Domain\Shared\PhoneNumber\PhoneNumber;
use Core\Domain\User\Entity\User;

class UserApplicationQueryMapper implements UserApplicationQueryMapperInterface
{
    public function toPhoneNumberResponseDto(?PhoneNumber $phoneNumber, ?PhoneCodeResponseDto $phoneCodeResponseDto): ?PhoneNumberResponseDto
    {
        if ($phoneNumber === null) {
            return null;
        }
        return new PhoneNumberResponseDto(
            code: $phoneCodeResponseDto,
            number: $phoneNumber->number
        );
    }

    public function toUserResponseDto(?User $user, ?PhoneNumberResponseDto $phoneNumberResponseDto, ?array $languageResponseDtoList = null, ?CountryResponseDto $countryResponseDto = null): ?UserResponseDto
    {
        if ($user === null) {
            return null;
        }
        return new UserResponseDto(
            uuid: $user->uuid->value,
            username: $user->username,
            usernameCode: $user->usernameCode,
            email: $user->email,
            emailConfirmed: $user->emailConfirmed,
            phone: $phoneNumberResponseDto,
            phoneConfirmed: $user->phoneConfirmed,
            createdAt: $user->createdAt,
            updatedAt: $user->updatedAt,
            firstName: $user->firstName,
            lastName: $user->lastName,
            middleName: $user->middleName,
            languages: $languageResponseDtoList,
            localeLanguageIsoCode: $user->localeLanguageIsoCode,
            country: $countryResponseDto,
            imageUuid: $user->imageUuid,
            color: $user->color,
            description: $user->description
        );
    }
}
