<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Command;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\CreatePhoneCodeCommandDto;

interface CreatePhoneCodeUseCase
{
    public function execute(CreatePhoneCodeCommandDto $phoneCodeRequestDto): PhoneCodeResponseDto;
}
