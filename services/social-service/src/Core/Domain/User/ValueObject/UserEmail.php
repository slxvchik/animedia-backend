<?php

declare(strict_types=1);

namespace Core\Domain\User\ValueObject;

final readonly class UserEmail
{
    public function __construct(
        public string $userUuid,
        public string $email
    ) {}
}
