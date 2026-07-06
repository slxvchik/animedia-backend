<?php

declare(strict_types=1);

namespace Core\Domain\Shared\SlugAlias;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class InvalidSlugAliasException extends AppException
{
    public function __construct(string $alias)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'slug_alias.invalid', [$alias]);
    }
}
