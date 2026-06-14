<?php

declare(strict_types=1);

namespace Core\Application\User\UseCase\Query;

use Core\Application\User\DTO\UserResponseDto;

interface GetUserListUseCase
{
    /**
     * @param string[] $userUuidList
     */
    public function execute(array $userUuidList): UserResponseDto;
}
