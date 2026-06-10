<?php

declare(strict_types=1);

namespace Core\Domain\Shared\IdentityGenerator;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidUuidException extends AppException
{
    public function __construct(string $uuid)
    {
        parent::__construct(AppExceptionStatus::INTERNAL_ERROR, 'internal.invalid.uuid', [$uuid]);
    }
}
