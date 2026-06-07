<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class PhoneCodeNotFoundException extends AppException
{
    public function __construct(string $countryIsoCode)
    {
        parent::__construct(AppExceptionStatus::ALREADY_EXISTS, 'phone_code.not_found', [$countryIsoCode]);
    }
}
