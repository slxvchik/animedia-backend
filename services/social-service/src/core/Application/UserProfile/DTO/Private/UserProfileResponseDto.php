<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO\Private;

use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Application\UserProfile\DTO\PhoneNumberDto;

final readonly class UserProfileResponseDto
{
    /**
     * @param UserProfileResponseDto[]|null $languages
     */
    public function __construct(
        public string              $userUuid,
        public string              $username,
        public string              $usernameCode,
        public string              $email,
        public ?string             $firstName,
        public ?string             $lastName,
        public ?string             $middleName,
        public ?array              $languages,
        public ?PhoneNumberDto     $phoneNumberDto,
        public ?CountryResponseDto $country,
        public ?string             $imageUuid,
        public ?string             $color,
        public ?string             $description,
        public bool                $emailConfirmed
    ) {}
}
