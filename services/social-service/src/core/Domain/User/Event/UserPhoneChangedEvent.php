<?php

namespace Core\Domain\User\Event;

use Core\Domain\Shared\ValueObject\PhoneNumber;

final readonly class UserPhoneChangedEvent
{
    public function __construct(
        public string $userUuid,
        public PhoneNumber $phone
    ) {}
}
