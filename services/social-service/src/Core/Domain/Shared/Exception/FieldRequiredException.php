<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class FieldRequiredException extends AppException
{
    public function __construct(string $entity, string $field)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'field.required', [$entity, $field]);
    }
}
