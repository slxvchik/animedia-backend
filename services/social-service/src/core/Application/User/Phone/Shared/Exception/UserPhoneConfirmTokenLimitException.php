<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\Shared\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserPhoneConfirmTokenLimitException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.phone.token.limit_exhausted');
    }
}
