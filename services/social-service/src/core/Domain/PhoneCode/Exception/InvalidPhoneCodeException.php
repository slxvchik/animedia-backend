<?php

namespace Core\Domain\PhoneCode\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidPhoneCodeException extends AppException
{
    public function __construct(?string $phoneCode)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'phone.invalid.code', [$phoneCode]);
    }
}
