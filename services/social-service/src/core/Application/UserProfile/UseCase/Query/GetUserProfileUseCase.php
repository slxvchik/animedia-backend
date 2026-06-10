<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Query;

use Core\Application\UserProfile\DTO\UserProfileResponseDto;

interface GetUserProfileUseCase
{
    public function execute(string $userUuid): UserProfileResponseDto;
}
