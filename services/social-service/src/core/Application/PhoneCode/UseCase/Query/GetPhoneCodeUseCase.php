<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;

interface GetPhoneCodeUseCase
{
    public function execute(string $phoneCodeUuid): PhoneCodeResponseDto;
}
