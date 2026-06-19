<?php

declare(strict_types=1);

namespace Core\Application\User\Query\Shared\Exception;

use Core\Domain\Shared\AppException\AppException;
use Core\Domain\Shared\AppException\AppExceptionStatus;

class UsersNotFoundException extends AppException
{
    /**
     * @param array $uuidList
     */
    public function __construct(array $uuidList)
    {
        parent::__construct(AppExceptionStatus::NOT_FOUND, 'user.not_found', $uuidList);
    }
}
