<?php

namespace Core\Application\User\Email\Confirm;

interface ConfirmUserEmailUseCase
{
    public function execute(string $token): void;
}
