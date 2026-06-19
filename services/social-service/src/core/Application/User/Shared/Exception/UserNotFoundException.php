<?php

declare(strict_types=1);

namespace Core\Application\User\Shared\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class UserNotFoundException extends AppException
{
    public function __construct(string $uuid)
    {
        parent::__construct(AppExceptionStatus::NOT_FOUND, 'user.not_found', [$uuid]);
    }
}
