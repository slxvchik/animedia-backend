<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\ValueObject;

readonly class SearchUserProfile
{
    public function __construct(
        public ?string $username = null,
        public ?string $firstName = null,
        public ?string $lastName = null,
        public ?string $languageIsoCode = null
    ) {}
}
