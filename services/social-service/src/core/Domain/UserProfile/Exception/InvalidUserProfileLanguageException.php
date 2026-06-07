<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidUserProfileLanguageException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, "user_profile.invalid.language");
    }
}
