<?php

namespace Core\Application\User\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class UserNotFoundException extends AppException
{
    public function __construct(string $uuid)
    {
        parent::__construct(AppExceptionStatus::NOT_FOUND, 'user.not_found', [$uuid]);
    }
}
