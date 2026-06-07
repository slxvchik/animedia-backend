<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Command;

use Core\Application\UserProfile\DTO\CommandUserProfileRequestDto;
use Core\Application\UserProfile\DTO\UserProfilePrivateResponseDto;

interface UpdateUserProfileUseCase
{
    public function execute(CommandUserProfileRequestDto $userProfileDto): UserProfilePrivateResponseDto;
}
