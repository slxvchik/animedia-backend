<?php

namespace Core\Application\PhoneCode\UseCase;

use Core\Application\PhoneCode\DTO\PhoneCodeDto;

interface UpdatePhoneCodeUseCase
{
    public function execute(PhoneCodeDto $phoneCodeDto): PhoneCodeDto;
}
