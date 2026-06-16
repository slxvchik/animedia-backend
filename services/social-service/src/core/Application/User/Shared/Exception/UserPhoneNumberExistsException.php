<?php

namespace Core\Application\User\Shared\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserPhoneNumberExistsException extends AppException
{
    public function __construct(string $phoneCode, string $phoneNumber)
    {
        parent::__construct(AppExceptionStatus::NOT_FOUND, 'user.phone.exists', [$phoneCode, $phoneNumber]);
    }
}
