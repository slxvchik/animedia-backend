<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class PhoneCodeExistsException extends AppException
{
    public function __construct()
    {
        parent::__construct(AppExceptionStatus::ALREADY_EXISTS, 'phone_code.exists');
    }
}
