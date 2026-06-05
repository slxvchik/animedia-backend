<?php

namespace Core\Domain\UserProfile\ValueObject;

readonly class SearchUserProfile
{
    public function __construct(
        public ?string $username,
        public ?string $firstName,
        public ?string $lastName,
        public ?string $languageIsoCode
    ) {}
}
