<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Uuid;

interface UuidGeneratorInterface
{
    public function generate(): string;
}
