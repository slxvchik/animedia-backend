<?php

namespace Code\Application\UserProfile\UseCase\Private;

use Code\Application\UserProfile\DTO\Private\UserProfileRequestDto;
use Code\Application\UserProfile\DTO\Private\UserProfileResponseDto;

interface UpdateUserProfileUseCase
{
    public function execute(UserProfileRequestDto $userProfileDto): UserProfileResponseDto;
}
