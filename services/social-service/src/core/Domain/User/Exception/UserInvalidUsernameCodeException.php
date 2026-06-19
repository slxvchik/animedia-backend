<?php

declare(strict_types=1);

namespace Core\Domain\User\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class UserInvalidUsernameCodeException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, "user.invalid.nickname_code");
    }
}
