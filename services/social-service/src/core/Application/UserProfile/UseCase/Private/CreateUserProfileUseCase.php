<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Private;

use Core\Application\UserProfile\DTO\Private\UserProfileResponseDto;

interface CreateUserProfileUseCase
{
    public function execute(UserProfileResponseDto $userProfileDto): UserProfileResponseDto;
}
