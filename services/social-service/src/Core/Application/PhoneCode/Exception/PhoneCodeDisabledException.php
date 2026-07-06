<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class PhoneCodeDisabledException extends AppException
{
    public function __construct(string $phoneCodeUuid)
    {
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'phone_code.disabled', [$phoneCodeUuid]);
    }
}
