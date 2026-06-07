<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Private;

use Core\Application\UserProfile\DTO\Private\UserProfileRequestDto;
use Core\Application\UserProfile\DTO\Private\UserProfileResponseDto;

interface UpdateUserProfileUseCase
{
    public function execute(UserProfileRequestDto $userProfileDto): UserProfileResponseDto;
}
