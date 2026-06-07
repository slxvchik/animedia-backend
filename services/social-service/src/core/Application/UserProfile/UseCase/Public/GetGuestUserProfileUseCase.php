<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Public;

use Core\Application\UserProfile\DTO\Public\UserProfileResponseDto;

interface GetGuestUserProfileUseCase
{
    public function execute(string $username, string $usernameCode): UserProfileResponseDto;
}
