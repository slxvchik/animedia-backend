<?php

declare(strict_types=1);

namespace Core\Domain\Language\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidLanguageIsoCodeException extends AppException
{
    public function __construct(string $isoCode)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'language.invalid.iso_code', [$isoCode]);
    }
}
