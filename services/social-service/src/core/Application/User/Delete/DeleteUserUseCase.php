<?php

declare(strict_types=1);

namespace Core\Application\User\Delete;

interface DeleteUserUseCase
{
    public function execute(string $userUuid): void;
}
