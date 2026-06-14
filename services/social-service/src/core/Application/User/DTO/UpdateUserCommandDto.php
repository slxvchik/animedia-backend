<?php

declare(strict_types=1);

namespace Core\Application\User\DTO;

final readonly class UpdateUserCommandDto
{
    /**
     * @param string[]|null $languageIsoCodeList
     */
    public function __construct(
        public string                 $localeLanguageIsoCode,
        public string                 $userUuid,
        public string                 $username,
        public string                 $usernameCode,
        public string                 $email,
        public bool                   $emailConfirmed,
        public ?PhoneNumberCommandDto $phoneNumberDto,
        public bool                   $phoneConfirmed,
        public ?string                $firstName,
        public ?string                $lastName,
        public ?string                $middleName,
        public ?array                 $languageIsoCodeList,
        public ?string                $countryIsoCode,
        public ?string                $imageUuid,
        public ?string                $color,
        public ?string                $description
    ) {}
}
