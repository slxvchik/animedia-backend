<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Command;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\UpdatePhoneCodeCommandDto;

interface UpdatePhoneCodeUseCase
{
    public function execute(UpdatePhoneCodeCommandDto $phoneCodeRequestDto): PhoneCodeResponseDto;
}
