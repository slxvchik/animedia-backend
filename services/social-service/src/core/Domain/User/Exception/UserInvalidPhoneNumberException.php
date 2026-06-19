<?php

declare(strict_types=1);

namespace Core\Domain\User\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class UserInvalidPhoneNumberException extends AppException
{
    public function __construct(string $phoneNumber)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'phone.invalid.number', [$phoneNumber]);
    }
}
