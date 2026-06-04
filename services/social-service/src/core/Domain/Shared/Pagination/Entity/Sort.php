<?php

namespace Core\Domain\Shared\Pagination\Entity;

readonly class Sort
{
    public function __construct(
        public string $fieldName,
        public Direction $sortDirection
    ) {}
}
