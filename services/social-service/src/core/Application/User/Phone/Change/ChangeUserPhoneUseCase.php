<?php

namespace Core\Application\User\Phone\Change;

use Core\Application\User\Email\Change\ChangeUserPhoneNumberCommandDto;

interface ChangeUserPhoneUseCase
{
    public function execute(string $userUuid, ?ChangeUserPhoneNumberCommandDto $newPhone): void;
}
