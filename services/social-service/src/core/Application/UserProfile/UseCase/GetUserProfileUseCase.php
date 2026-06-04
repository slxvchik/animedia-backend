<?php

namespace Code\Application\UserProfile\UseCase;

use Code\Application\UserProfile\DTO\UserProfileDTO;

interface GetUserProfileUseCase
{
    public function execute(string $userUuid): UserProfileDTO;
}
