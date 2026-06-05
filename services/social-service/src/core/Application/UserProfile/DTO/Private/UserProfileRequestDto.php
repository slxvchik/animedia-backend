<?php

namespace Code\Application\UserProfile\DTO\Private;

use Code\Application\UserProfile\DTO\PhoneNumberDto;

final readonly class UserProfileRequestDto
{
    /**
     * @param string[]|null $languageIsoCodeList
     */
    public function __construct(
        public string $userUuid,
        public string $username,
        public string $usernameCode,
        public string $email,
        public ?string $firstName,
        public ?string $lastName,
        public ?string $middleName,
        public ?array $languageIsoCodeList,
        public ?PhoneNumberDto $phoneNumberDto,
        public ?string $countryIsoCode,
        public ?string $imageUuid,
        public ?string $color,
        public ?string $description,
        public bool $emailConfirmed
    ) {}
}
