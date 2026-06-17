<?php

declare(strict_types=1);

namespace Core\Domain\Shared\IdentityGenerator;

interface IdentityGeneratorInterface
{
    public function generate(): string;
}
