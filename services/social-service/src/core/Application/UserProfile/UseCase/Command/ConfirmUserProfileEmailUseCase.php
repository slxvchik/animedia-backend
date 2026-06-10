<?php

namespace Core\Application\UserProfile\UseCase\Command;

interface ConfirmUserProfileEmailUseCase
{
    public function execute(string $token): void;
}
