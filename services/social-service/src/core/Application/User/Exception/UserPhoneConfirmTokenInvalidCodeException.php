<?php

declare(strict_types=1);

namespace Core\Application\User\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserPhoneConfirmTokenInvalidCodeException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'user.phone.token.invalid_code');
    }
}
