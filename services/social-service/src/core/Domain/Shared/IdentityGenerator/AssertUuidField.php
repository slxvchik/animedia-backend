<?php

namespace Core\Domain\Shared\IdentityGenerator;

trait AssertUuidField
{
    private function assertUuid(string $uuid): void
    {
        if (!preg_match('/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i', $uuid)) {
            throw new InvalidUuidException($uuid);
        }
    }
}
