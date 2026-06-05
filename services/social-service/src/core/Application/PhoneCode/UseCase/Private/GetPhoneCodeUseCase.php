<?php

namespace Core\Application\PhoneCode\UseCase\Private;

use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;

interface GetPhoneCodeUseCase
{
    public function execute(string $phoneCode): PhoneCodeResponseDto;
}
