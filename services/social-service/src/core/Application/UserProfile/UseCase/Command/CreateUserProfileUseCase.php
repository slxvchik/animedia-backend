<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Command;

use Core\Application\UserProfile\DTO\UserProfilePrivateResponseDto;

interface CreateUserProfileUseCase
{
    public function execute(UserProfilePrivateResponseDto $userProfileDto): UserProfilePrivateResponseDto;
}
