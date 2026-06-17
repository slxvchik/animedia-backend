<?php

declare(strict_types=1);

namespace Core\Application\User\Email\Confirm;

interface ConfirmUserEmailUseCase
{
    public function execute(string $token): void;
}
