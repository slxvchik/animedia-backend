<?php

namespace Core\Application\PhoneCode\UseCase;

use Core\Application\PhoneCode\DTO\PhoneCodeDto;

interface CreatePhoneCodeUseCase
{
    public function execute(PhoneCodeDto $phoneCodeDto): PhoneCodeDto;
}
