<?php

namespace Core\Domain\UserProfile\Events;

final class UserEmailConfirmEvent
{
    public function __construct(
        public readonly string $email,
        private ?string $token = null
    ) {
        // TODO: generate token with date timeout
    }

    public function isTokenValid(): void
    {
        print_r($this->token);
    }

    private function generateToken(): void
    {
        $this->token = 'test';//bin2hex(random_bytes(32));
    }
}
