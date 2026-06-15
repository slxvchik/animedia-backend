<?php

declare(strict_types=1);

namespace Core\Application\User\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserPhoneConfirmTokenNotFoundException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::NOT_FOUND, 'user.phone.token.not_found');
    }
}
