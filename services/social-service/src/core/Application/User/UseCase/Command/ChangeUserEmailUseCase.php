<?php

namespace Core\Application\User\UseCase\Command;

interface ChangeUserEmailUseCase
{
    public function execute(string $userUuid, string $email): void;
}
