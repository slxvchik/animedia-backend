<?php

declare(strict_types=1);

namespace Core\Domain\User\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class UserEmailAlreadyConfirmedException extends AppException
{
    public function __construct(string $email)
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.email.confirm', [$email]);
    }
}
