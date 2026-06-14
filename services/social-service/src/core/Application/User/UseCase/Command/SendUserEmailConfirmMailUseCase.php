<?php

namespace Core\Application\User\UseCase\Command;

interface SendUserEmailConfirmMailUseCase
{
    public function execute(string $userUuid): void;
}
