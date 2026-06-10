<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Command;

use Core\Application\UserProfile\DTO\CreateUserProfileCommandDto;
use Core\Application\UserProfile\DTO\UserProfileResponseDto;

interface CreateUserProfileUseCase
{
    public function execute(CreateUserProfileCommandDto $userProfileDto): UserProfileResponseDto;
}
