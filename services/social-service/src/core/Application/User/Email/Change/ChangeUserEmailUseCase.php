<?php

namespace Core\Application\User\Email\Change;

interface ChangeUserEmailUseCase
{
    public function execute(string $userUuid, string $email): void;
}
