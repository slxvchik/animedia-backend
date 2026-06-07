<?php

declare(strict_types=1);

namespace Core\Application\Shared\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidTypeException extends AppException
{
    public function __construct(
        mixed $actualValue,
        string $requiredType
    ) {
        $actualType = get_debug_type($actualValue);
        parent::__construct(
            appExceptionStatus: AppExceptionStatus::INVALID_ARGUMENT,
            args: [ $actualType, $requiredType ]
        );
    }
}
