<?php

declare(strict_types=1);

namespace Core\Domain\PhoneVerificationToken\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class PhoneVerificationTokenUsedException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.phone.token.attempts_limit_exhausted');
    }
}
