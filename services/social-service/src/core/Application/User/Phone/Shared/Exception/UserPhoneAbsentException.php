<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\Shared\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserPhoneAbsentException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::NOT_FOUND, 'user.phone.absent');
    }
}
