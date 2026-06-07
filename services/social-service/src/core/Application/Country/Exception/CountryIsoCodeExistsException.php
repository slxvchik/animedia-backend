<?php

declare(strict_types=1);

namespace Core\Application\Country\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class CountryIsoCodeExistsException extends AppException
{

    public function __construct(
        string $countryIsoCode
    ) {
        parent::__construct(
            appExceptionStatus: AppExceptionStatus::ALREADY_EXISTS,
            errorCode: 'country.code.exists',
            args: [$countryIsoCode]
        );
    }
}
