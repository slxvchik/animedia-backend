<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetUser;

use Core\Application\User\Query\Shared\DTO\UserResponseDto;

interface GetUserUseCase
{
    public function execute(string $userUuid): UserResponseDto;
}
