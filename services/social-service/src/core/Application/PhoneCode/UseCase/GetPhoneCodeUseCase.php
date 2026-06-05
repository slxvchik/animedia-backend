<?php

namespace Core\Application\PhoneCode\UseCase;

use Core\Application\PhoneCode\DTO\PhoneCodeDto;

interface GetPhoneCodeUseCase
{
    public function execute(string $phoneCode): PhoneCodeDto;
}
