<?php

namespace Core\Application\UserProfile\Mapper;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\UserProfile\DTO\CreateUserProfileCommandDto;
use Core\Application\UserProfile\DTO\PhoneNumberResponseDto;
use Core\Application\UserProfile\DTO\UpdateUserProfileCommandDto;
use Core\Application\UserProfile\DTO\UserProfileResponseDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileApplicationMapperInterface
{
    public function fromCreateUserProfileCommandDto(CreateUserProfileCommandDto $userProfileRequestDto, string $generatedUuid): UserProfile;

    public function fromUpdateUserProfileCommandDto(UpdateUserProfileCommandDto $userProfileRequestDto): UserProfile;

    /**
     * @param LanguageResponseDto[]|null $languageResponseDtoList
     */
    public function toUserProfileResponseDto(?UserProfile $userProfile, ?PhoneNumberResponseDto $phoneNumberResponseDto = null, ?array $languageResponseDtoList = null, ?CountryResponseDto $countryResponseDto = null): ?UserProfileResponseDto;
}
