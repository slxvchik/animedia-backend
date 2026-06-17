<?php

declare(strict_types=1);

namespace Core\Domain\Shared\SlugAlias;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;

class InvalidSlugAliasException extends AppException
{
    public function __construct(string $alias)
    {
        parent::__construct(AppExceptionStatus::INVALID_ARGUMENT, 'slug_alias.invalid', [$alias]);
    }
}
