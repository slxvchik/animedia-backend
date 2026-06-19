<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class InvalidPhoneCodeException extends AppException
{
    public function __construct(?string $phoneCode = null)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'phone_code.invalid.code', [$phoneCode]);
    }
}
