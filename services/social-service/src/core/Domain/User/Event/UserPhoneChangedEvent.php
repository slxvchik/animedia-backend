<?php

namespace Core\Domain\User\Event;

final readonly class UserPhoneChangedEvent
{
    public function __construct(
        public string $userUuid
    ) {}
}
