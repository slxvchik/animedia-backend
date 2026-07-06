<?php

declare(strict_types=1);

namespace Core\Domain\PhoneVerificationToken\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class PhoneVerificationTokenInvalidCodeException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'user.phone.token.invalid_code');
    }
}
