<?php

namespace Code\Application\UserProfile\UseCase\Private;

interface DeleteUserProfileUseCase
{
    public function execute(string $userUuid): void;
}
