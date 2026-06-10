<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Query;

use Core\Application\UserProfile\DTO\UserProfileResponseDto;

interface GetUserProfileListUseCase
{
    /**
     * @param string[] $userUuidList
     */
    public function execute(array $userUuidList): UserProfileResponseDto;
}
