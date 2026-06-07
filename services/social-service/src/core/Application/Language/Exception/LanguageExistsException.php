<?php

declare(strict_types=1);

namespace Core\Application\Language\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class LanguageExistsException extends AppException
{
    public function __construct(string $languageIsoCode)
    {
        parent::__construct(
            AppExceptionStatus::ALREADY_EXISTS,
            'language.iso_code.exists',
            [$languageIsoCode]
        );
    }
}
