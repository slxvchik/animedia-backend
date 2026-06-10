<?php

namespace Core\Application\UserProfile\UseCase\Command;

interface ChangeUserProfileEmailUseCase
{
    public function execute(string $email): void;
}
