<?php

declare(strict_types=1);

namespace Core\Application\User\Email\SendConfirm;

interface SendUserEmailConfirmMailUseCase
{
    public function execute(string $userUuid): void;
}
