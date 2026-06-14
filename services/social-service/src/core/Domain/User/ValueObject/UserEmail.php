<?php

namespace Core\Domain\User\ValueObject;

final readonly class UserEmail
{
    public function __construct(
        public string $userUuid,
        public string $email
    ) {}
}
