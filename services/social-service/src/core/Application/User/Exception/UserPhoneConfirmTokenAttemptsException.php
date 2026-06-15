<?php

declare(strict_types=1);

namespace Core\Application\User\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserPhoneConfirmTokenAttemptsException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.phone.token.attempts_limit_exhausted');
    }
}
