<?php

declare(strict_types=1);

namespace Core\Application\User\Update;

interface UpdateUserUseCase
{
    public function execute(UpdateUserCommandDto $userDto): void;
}
