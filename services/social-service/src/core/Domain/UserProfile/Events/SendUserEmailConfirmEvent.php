<?php

namespace Core\Domain\UserProfile\Events;

final readonly class SendUserEmailConfirmEvent
{
    public function __construct(
        public string $email,
        public string $token
    ) {}
}
