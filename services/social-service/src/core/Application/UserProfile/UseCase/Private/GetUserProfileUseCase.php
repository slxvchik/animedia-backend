<?php

namespace Code\Application\UserProfile\UseCase\Private;

use Code\Application\UserProfile\DTO\Private\UserProfileResponseDto;

interface GetUserProfileUseCase
{
    public function execute(string $username, string $usernameCode): UserProfileResponseDto;
}
