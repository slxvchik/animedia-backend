<?php

declare(strict_types=1);

namespace Core\Application\Country\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class CountryNotFoundException extends AppException
{

    public function __construct(
        string $countryIsoCode
    ) {
        parent::__construct(
            appExceptionStatus: AppExceptionStatus::NOT_FOUND,
            errorCode: 'country.not_found',
            args: [$countryIsoCode]
        );
    }
}
