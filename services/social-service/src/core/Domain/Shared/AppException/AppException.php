<?php

declare(strict_types=1);

namespace Core\Domain\Shared\AppException;

use RuntimeException;

class AppException extends RuntimeException
{
    /**
     * @param AppExceptionStatus $appExceptionStatus
     * @param string $errorCode
     * @param string[] $args
     */
    public function __construct(
        public readonly AppExceptionStatus $appExceptionStatus = AppExceptionStatus::INTERNAL_ERROR,
        public readonly string $errorCode = 'app.internal_error',
        public readonly array $args = []
    ) {
        foreach ($args as $arg) {
            if (!is_string($arg)) {
                throw new RuntimeException("AppException: argument $arg is not a string");
            }
        }

        parent::__construct(message: "AppException: $errorCode");
    }
}
