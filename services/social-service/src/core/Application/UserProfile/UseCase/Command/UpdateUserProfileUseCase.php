<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Command;

use Core\Application\PhoneCode\DTO\UpdatePhoneCodeCommandDto;
use Core\Application\UserProfile\DTO\UserProfileResponseDto;

interface UpdateUserProfileUseCase
{
    public function execute(UpdatePhoneCodeCommandDto $userProfileDto): UserProfileResponseDto;
}
