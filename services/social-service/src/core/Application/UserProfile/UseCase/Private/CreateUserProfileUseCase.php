<?php

namespace Code\Application\UserProfile\UseCase\Private;

use Code\Application\UserProfile\DTO\Private\UserProfileResponseDto;

interface CreateUserProfileUseCase
{
    public function execute(UserProfileResponseDto $userProfileDto): UserProfileResponseDto;
}
