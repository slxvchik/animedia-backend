<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Pagination\Entity;

final readonly class Sort
{
    public function __construct(
        public string $fieldName,
        public Direction $sortDirection
    ) {}
}
