<?php

namespace Core\Domain\UserProfile\Events;

final readonly class UserPasswordChangeEvent
{
    public function __construct(
        public string $email,
        public string $token,
        public \DateTimeImmutable $createdAt
    ) {}
}
