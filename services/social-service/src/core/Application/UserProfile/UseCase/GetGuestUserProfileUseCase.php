<?php

namespace Code\Application\UserProfile\UseCase;

use Code\Application\UserProfile\DTO\PublicUserProfileDto;

interface GetGuestUserProfileUseCase
{
    public function execute(string $username, string $usernameCode): PublicUserProfileDto;
}
