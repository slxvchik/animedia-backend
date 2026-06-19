<?php

declare(strict_types=1);

namespace Core\Domain\Shared\AppException;

enum AppExceptionStatus
{
    case NOT_FOUND;
    case ALREADY_EXISTS;
    case INVALID_ARGUMENT;
    case BUSINESS_ERROR;
    case INTERNAL_ERROR;
}
