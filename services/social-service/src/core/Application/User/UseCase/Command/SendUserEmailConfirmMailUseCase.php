<?php

namespace Core\Application\User\UseCase\Command;

interface SendUserEmailConfirmMailUseCase
{
    public function execute(string $userUuid, string $email, string $generatedToken): void;
}
