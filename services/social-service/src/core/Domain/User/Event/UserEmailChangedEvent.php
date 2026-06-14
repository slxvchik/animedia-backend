<?php

namespace Core\Domain\User\Event;

use Core\Domain\User\ValueObject\UserEmail;

final readonly class UserEmailChangedEvent
{
    public function __construct(
        public UserEmail $email
    ) {}
}
