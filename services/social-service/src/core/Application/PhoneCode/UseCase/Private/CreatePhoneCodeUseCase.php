<?php

namespace Core\Application\PhoneCode\UseCase\Private;

use Core\Application\PhoneCode\DTO\Private\PhoneCodeRequestDto;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;

interface CreatePhoneCodeUseCase
{
    public function execute(PhoneCodeRequestDto $phoneCodeRequestDto): PhoneCodeResponseDto;
}
