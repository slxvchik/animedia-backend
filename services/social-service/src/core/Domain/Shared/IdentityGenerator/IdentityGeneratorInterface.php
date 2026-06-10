<?php

namespace Core\Domain\Shared\IdentityGenerator;

interface IdentityGeneratorInterface
{
    public function generate(): string;
}
