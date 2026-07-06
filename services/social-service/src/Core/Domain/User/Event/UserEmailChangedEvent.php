<?php

declare(strict_types=1);

namespace Core\Domain\User\Event;

final readonly class UserEmailChangedEvent
{
    public function __construct(
        public string $userUuid
    ) {}
}
