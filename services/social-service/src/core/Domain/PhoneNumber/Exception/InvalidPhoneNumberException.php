<?php

namespace Core\Domain\PhoneNumber\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidPhoneNumberException extends AppException
{
    public function __construct(string $phoneNumber)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'phone.invalid.number', [$phoneNumber]);
    }
}
