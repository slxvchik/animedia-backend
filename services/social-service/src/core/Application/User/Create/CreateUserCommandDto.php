<?php

declare(strict_types=1);

namespace Core\Application\User\Create;

final readonly class CreateUserCommandDto
{
    public function __construct(
        public string $username,
        public string $usernameCode,
        public string $email,
        public bool   $emailConfirmed,
        public string $localeLanguageIsoCode
    ) {}
}
