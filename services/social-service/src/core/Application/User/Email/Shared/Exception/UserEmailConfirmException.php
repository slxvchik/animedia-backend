<?php

declare(strict_types=1);

namespace Core\Application\User\Email\Shared\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserEmailConfirmException extends AppException
{
    public function __construct(string $curEmail, string $tokenEmail)
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.email.confirm_email_not_equal_requested', [$curEmail, $tokenEmail]);
    }
}
