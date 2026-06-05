<?php

namespace Code\Application\UserProfile\DTO\Public;

final readonly class UserProfileSearchRequestDto
{
    public function __construct(
        public ?string $username,
        public ?string $usernameCode,
        public ?string $firstName,
        public ?string $lastName,
        public ?string $countryIsoCode
    ) {}
}
