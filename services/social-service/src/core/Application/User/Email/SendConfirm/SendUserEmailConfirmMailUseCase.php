<?php

namespace Core\Application\User\Email\SendConfirm;

interface SendUserEmailConfirmMailUseCase
{
    public function execute(string $userUuid): void;
}
