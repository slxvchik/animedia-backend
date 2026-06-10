<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class EmailAlreadyConfirmedException extends AppException
{
    public function __construct(string $email)
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user_profile.email.confirm', [$email]);
    }
}
