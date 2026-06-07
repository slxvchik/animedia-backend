<?php

declare(strict_types=1);

namespace Core\Application\Language\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class LanguageNotFoundException extends AppException
{
    public function __construct(?string $languageIsoCode = null)
    {
        if ($languageIsoCode === null) {
            parent::__construct(AppExceptionStatus::NOT_FOUND, 'language.iso_code.not_found.extra', [$languageIsoCode]);
        } else {
            parent::__construct(AppExceptionStatus::NOT_FOUND, 'language.iso_code.not_found');
        }
    }
}
