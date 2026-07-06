<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetUserList;

use Core\Application\User\Query\Shared\DTO\UserResponseDto;

interface GetUserListUseCase
{
    /**
     * @param string[] $userUuidList
     * @return UserResponseDto[]
     */
    public function execute(array $userUuidList): array;
}
