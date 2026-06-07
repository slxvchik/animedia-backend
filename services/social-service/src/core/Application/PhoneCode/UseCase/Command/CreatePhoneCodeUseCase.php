<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Command;

use Core\Application\PhoneCode\DTO\PhoneCodePrivateResponseDto;
use Core\Application\PhoneCode\DTO\CommandPhoneCodeRequestDto;

interface CreatePhoneCodeUseCase
{
    public function execute(CommandPhoneCodeRequestDto $phoneCodeRequestDto): PhoneCodePrivateResponseDto;
}
