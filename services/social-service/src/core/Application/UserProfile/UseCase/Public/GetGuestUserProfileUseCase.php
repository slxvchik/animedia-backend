<?php

namespace Code\Application\UserProfile\UseCase\Public;

use Code\Application\UserProfile\DTO\Public\UserProfileResponseDto;

interface GetGuestUserProfileUseCase
{
    public function execute(string $username, string $usernameCode): UserProfileResponseDto;
}
