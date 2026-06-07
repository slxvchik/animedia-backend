<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Private;

interface DeleteUserProfileUseCase
{
    public function execute(string $userUuid): void;
}
