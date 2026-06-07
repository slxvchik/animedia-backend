<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Private;

use Core\Application\UserProfile\DTO\Private\UserProfileResponseDto;

interface GetUserProfileUseCase
{
    public function execute(string $username, string $usernameCode): UserProfileResponseDto;
}
