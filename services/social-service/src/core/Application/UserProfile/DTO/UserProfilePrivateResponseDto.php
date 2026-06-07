<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO;

use Core\Application\Country\DTO\CountryPublicResponseDto;

final readonly class UserProfilePrivateResponseDto
{
    /**
     * @param UserProfilePrivateResponseDto[]|null $languages
     */
    public function __construct(
        public string                    $userUuid,
        public string                    $username,
        public string                    $usernameCode,
        public string                    $email,
        public ?string                   $firstName,
        public ?string                   $lastName,
        public ?string                   $middleName,
        public ?array                    $languages,
        public ?PhoneNumberDto           $phoneNumberDto,
        public ?CountryPublicResponseDto $country,
        public ?string                   $imageUuid,
        public ?string                   $color,
        public ?string                   $description,
        public bool                      $emailConfirmed
    ) {}
}
