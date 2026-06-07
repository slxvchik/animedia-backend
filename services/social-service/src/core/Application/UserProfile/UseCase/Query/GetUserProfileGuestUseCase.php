<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Query;

use Core\Application\UserProfile\DTO\UserProfilePublicResponseDto;

interface GetUserProfileGuestUseCase
{
    public function execute(string $username, string $usernameCode): UserProfilePublicResponseDto;
}
