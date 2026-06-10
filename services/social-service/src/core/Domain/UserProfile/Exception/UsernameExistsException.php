<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UsernameExistsException extends AppException
{
    public function __construct(string $username, string $usernameCode)
    {
        parent::__construct(AppExceptionStatus::ALREADY_EXISTS, 'user_profile.username.exists', [$username, $usernameCode]);
    }
}
