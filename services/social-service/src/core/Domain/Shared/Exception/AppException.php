<?php

namespace Core\Domain\Shared\Exception;

use RuntimeException;

class AppException extends RuntimeException
{
    /**
     * @param AppExceptionStatus $appExceptionStatus
     * @param string $errorCode
     * @param string[] $args
     */
    public function __construct(
        private readonly AppExceptionStatus $appExceptionStatus = AppExceptionStatus::INTERNAL_ERROR,
        private readonly string $errorCode = 'app.internal_error',
        private readonly array $args = []
    ) {
        parent::__construct(message: "AppException: $errorCode");
    }

    /**
     * @return AppExceptionStatus
     */
    public function getAppExceptionStatus(): AppExceptionStatus
    {
        return $this->appExceptionStatus;
    }

    /**
     * @return string
     */
    public function getErrorCode(): string
    {
        return $this->errorCode;
    }

    /**
     * @return string[]
     */
    public function getArgs(): array
    {
        return $this->args;
    }
}
