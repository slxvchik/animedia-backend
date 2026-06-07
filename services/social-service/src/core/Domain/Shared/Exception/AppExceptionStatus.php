<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Exception;

enum AppExceptionStatus
{
    case NOT_FOUND;
    case ALREADY_EXISTS;
    case INVALID_ARGUMENT;
    case INTERNAL_ERROR;
}
