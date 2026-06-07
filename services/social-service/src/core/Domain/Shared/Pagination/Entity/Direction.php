<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Pagination\Entity;

enum Direction
{
    case DESC;
    case ASC;
}
