<?php

declare(strict_types=1);

namespace Core\Application\User\UseCase\Command;

use Core\Application\User\DTO\CreateUserCommandDto;

interface CreateUserUseCase
{
    public function execute(CreateUserCommandDto $userDto): string;
}
