<?php

declare(strict_types=1);

namespace Core\Domain\Country\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidCountryIsoCodeException extends AppException
{
    public function __construct(string $isoCode)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'country.invalid.iso_code', [$isoCode]);
    }
}
