<?php

declare(strict_types=1);

namespace Core\Application\User\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserUsernameExistsException extends AppException
{
    public function __construct(string $username, string $usernameCode)
    {
        parent::__construct(AppExceptionStatus::ALREADY_EXISTS, 'user.username.exists', [$username, $usernameCode]);
    }
}
