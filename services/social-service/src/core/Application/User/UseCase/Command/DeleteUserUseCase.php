<?php

declare(strict_types=1);

namespace Core\Application\User\UseCase\Command;

interface DeleteUserUseCase
{
    public function execute(string $userUuid): void;
}
