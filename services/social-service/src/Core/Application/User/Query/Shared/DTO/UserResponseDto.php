<?php

declare(strict_types=1);

namespace Core\Application\User\Query\Shared\DTO;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use DateTimeImmutable;

final readonly class UserResponseDto
{
    /**
     * @param LanguageResponseDto[]|null $languages
     */
    public function __construct(
        public string                  $uuid,
        public string                  $username,
        public string                  $usernameCode,
        public string                  $email,
        public bool                    $emailConfirmed,
        public ?PhoneNumberResponseDto $phone,
        public bool                    $phoneConfirmed,
        public DateTimeImmutable       $createdAt,
        public DateTimeImmutable       $updatedAt,
        public ?string                 $firstName,
        public ?string                 $lastName,
        public ?string                 $middleName,
        public ?array                  $languages,
        public string                  $localeLanguageIsoCode,
        public ?CountryResponseDto     $country,
        public ?string                 $imageUuid,
        public ?string                 $color,
        public ?string                 $description
    ) {}
}
