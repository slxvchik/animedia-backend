<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO;

final readonly class CreateUserProfileCommandDto
{
    public function __construct(
        public string $username,
        public string $usernameCode,
        public string $email,
        public bool   $emailConfirmed
    ) {}
}
