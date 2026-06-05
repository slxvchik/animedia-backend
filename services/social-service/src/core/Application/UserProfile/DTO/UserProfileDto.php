<?php

namespace Code\Application\UserProfile\DTO;

use Core\Application\Country\DTO\CountryDto;
use Core\Application\Language\DTO\LanguageDto;

final readonly class UserProfileDto
{
    /**
     * @param LanguageDto[]|null $languages
     */
    public function __construct(
        public string $userUuid,
        public string $username,
        public string $usernameCode,
        public string $email,
        public ?string $firstName,
        public ?string $lastName,
        public ?string $middleName,
        public ?array $languages,
        public ?PhoneNumberDto $phoneNumberDto,
        public ?CountryDto $country,
        public ?string $imageUuid,
        public ?string $color,
        public ?string $description,
        public bool $emailConfirmed
    ) {}
}
