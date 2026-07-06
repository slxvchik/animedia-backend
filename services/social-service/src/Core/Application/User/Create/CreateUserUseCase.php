<?php

declare(strict_types=1);

namespace Core\Application\User\Create;

interface CreateUserUseCase
{
    public function execute(CreateUserCommandDto $userDto): string;
}
