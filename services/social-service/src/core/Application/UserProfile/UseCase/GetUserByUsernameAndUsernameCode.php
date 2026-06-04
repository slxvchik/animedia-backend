<?php

namespace Code\Application\UserProfile\UseCase;

use Code\Application\UserProfile\DTO\UserProfileDTO;

interface GetUserByUsernameAndUsernameCode
{
    public function execute(string $username, string $usernameCode): UserProfileDTO;
}
