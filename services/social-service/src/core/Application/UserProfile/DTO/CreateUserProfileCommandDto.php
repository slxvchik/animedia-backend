<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO;

final readonly class CreateUserProfileCommandDto
{
    /**
     * @param string[]|null $languageIsoCodeList
     */
    public function __construct(
        public string          $userUuid,
        public string          $username,
        public string          $usernameCode,
        public string          $email,
        public ?string         $firstName,
        public ?string         $lastName,
        public ?string         $middleName,
        public ?array          $languageIsoCodeList,
        public ?PhoneNumberDto $phoneNumberDto,
        public bool            $emailConfirmed,
        public bool            $phoneConfirmed
    ) {}
}
