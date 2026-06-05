<?php

namespace Code\Application\UserProfile\UseCase;

use Code\Application\UserProfile\DTO\PublicUserProfileDto;

interface GetUserProfileUseCase
{
    public function execute(string $userUuid): PublicUserProfileDto;
}
