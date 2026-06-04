<?php

namespace Core\Domain\Pagination\Entity;

readonly class Pageable
{
    public function __construct(
        public int $page,
        public int $perPage,
        public ?Sort $sort
    ) {}
}
