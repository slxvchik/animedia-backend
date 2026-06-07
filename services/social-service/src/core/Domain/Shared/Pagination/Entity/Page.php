<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Pagination\Entity;

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
