<?php

declare(strict_types=1);

namespace Core\Application\User\UseCase\Query;

use Core\Application\User\DTO\UserResponseDto;

interface GetUserUseCase
{
    public function execute(string $userUuid): UserResponseDto;
}
