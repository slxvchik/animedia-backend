<?php

namespace Core\Domain\Pagination\Entity;

readonly class Page
{
    public function __construct(
        public mixed $content,
        public int $pageNumber,
        public int $pageSize,
        public int $totalPages,
        public int $totalCount,
        public bool $hasPrev,
        public bool $hasNext
    ) {}

    public function changeContent(mixed $newContent, Page $page): Page
    {
        return new Page(
            content: $newContent,
            pageNumber: $page->pageNumber,
            pageSize: $page->pageSize,
            totalPages: $page->totalPages,
            totalCount: $page->totalCount,
            hasPrev: $page->hasPrev,
            hasNext: $page->hasNext
        );
    }
}
