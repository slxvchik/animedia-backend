<?php

declare(strict_types=1);

namespace Core\Application\User\Shared\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class UserEmailExistsException extends AppException
{
    public function __construct(string $email)
    {
        parent::__construct(AppExceptionStatus::ALREADY_EXISTS, 'user.email.exists', [$email]);
    }
}
