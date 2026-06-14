<?php

declare(strict_types=1);

namespace Core\Application\User\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserConfirmEmailException extends AppException
{
    public function __construct(string $curEmail, string $tokenEmail)
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user_profile.email.confirm_email_not_equal_requested', [$curEmail, $tokenEmail]);
    }
}
