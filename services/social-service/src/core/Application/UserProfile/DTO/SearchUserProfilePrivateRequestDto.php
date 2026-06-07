<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO;

final readonly class SearchUserProfilePrivateRequestDto
{
    public function __construct(
        public ?string $userUuid,
        public ?string $email,
        public ?string $username,
        public ?string $usernameCode,
        public ?string $firstName,
        public ?string $lastName,
        public ?string $countryIsoCode
    ) {}
}
