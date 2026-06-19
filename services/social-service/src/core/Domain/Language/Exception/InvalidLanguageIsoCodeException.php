<?php

declare(strict_types=1);

namespace Core\Domain\Language\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class InvalidLanguageIsoCodeException extends AppException
{
    public function __construct(string $isoCode)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'language.invalid.iso_code', [$isoCode]);
    }
}
