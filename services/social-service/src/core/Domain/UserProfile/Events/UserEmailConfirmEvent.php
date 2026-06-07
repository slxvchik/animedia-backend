<?php

namespace Core\Domain\UserProfile\Events;

final readonly class UserEmailConfirmEvent
{
    public function __construct(
        public string $email,
        public string $token,
        public \DateTimeImmutable $dateTimeImmutable
    ) {}
}
