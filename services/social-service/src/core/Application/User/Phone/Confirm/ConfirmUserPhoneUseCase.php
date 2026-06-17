<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\Confirm;

interface ConfirmUserPhoneUseCase
{
    public function execute(string $userUuid, int $code): void;
}
