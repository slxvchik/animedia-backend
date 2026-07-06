<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Pagination;

/**
 * @template T
 */
final readonly class Page
{
    /**
     * @param T[] $content
     */
    public function __construct(
        public array $content,
        public int $pageNumber,
        public int $pageSize,
        public int $totalPages,
        public int $totalCount,
        public bool $hasPrev,
        public bool $hasNext
    ) {}

    public static function empty(): self
    {
        return new self(
            content: [],
            pageNumber: 0,
            pageSize: 0,
            totalPages: 0,
            totalCount: 0,
            hasPrev: false,
            hasNext: false
        );
    }

    /**
     * @param T[] $newContent
     * @return Page<T>
     */
    public function changeContent(array $newContent): Page
    {
        return new Page(
            content: $newContent,
            pageNumber: $this->pageNumber,
            pageSize: $this->pageSize,
            totalPages: $this->totalPages,
            totalCount: $this->totalCount,
            hasPrev: $this->hasPrev,
            hasNext: $this->hasNext
        );
    }
}
