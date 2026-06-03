<?php

namespace Core\Domain\User\Model;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidUserProfileNicknameException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, "user_profile.invalid.nickname");
    }
}
