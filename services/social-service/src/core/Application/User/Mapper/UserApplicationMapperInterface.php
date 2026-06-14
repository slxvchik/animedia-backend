<?php

namespace Core\Application\User\Mapper;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\User\DTO\CreateUserCommandDto;
use Core\Application\User\DTO\PhoneNumberResponseDto;
use Core\Application\User\DTO\UpdateUserCommandDto;
use Core\Application\User\DTO\UserResponseDto;
use Core\Domain\Shared\ValueObject\PhoneNumber;
use Core\Domain\User\Entity\User;

interface UserApplicationMapperInterface
{
    public function fromCreateUserCommandDto(CreateUserCommandDto $userRequestDto, string $generatedUuid): User;

    public function fromUpdateUserCommandDto(UpdateUserCommandDto $userRequestDto): User;

    public function toPhoneNumberResponseDto(?PhoneNumber $phoneNumber): ?PhoneNumberResponseDto;

    /**
     * @param LanguageResponseDto[]|null $languageResponseDtoList
     */
    public function toUserResponseDto(?User $user, ?array $languageResponseDtoList = null, ?CountryResponseDto $countryResponseDto = null): ?UserResponseDto;
}
