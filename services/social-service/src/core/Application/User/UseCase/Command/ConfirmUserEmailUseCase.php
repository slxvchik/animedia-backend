<?php

namespace Core\Application\User\UseCase\Command;

interface ConfirmUserEmailUseCase
{
    public function execute(string $token): void;
}
