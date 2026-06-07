<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Private;

use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;

interface GetPhoneCodeUseCase
{
    public function execute(string $countryIsoCode): PhoneCodeResponseDto;
}
