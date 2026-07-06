<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Pagination;

final readonly class Pageable
{
    public function __construct(
        public int $page,
        public int $perPage
    ) {}
}
