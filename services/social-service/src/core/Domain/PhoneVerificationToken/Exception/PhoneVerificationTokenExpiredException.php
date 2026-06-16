<?php

declare(strict_types=1);

namespace Core\Domain\PhoneVerificationToken\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class PhoneVerificationTokenExpiredException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.phone.tokenexpired');
    }
}
