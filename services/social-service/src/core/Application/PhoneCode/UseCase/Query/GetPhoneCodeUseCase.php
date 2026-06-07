<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodePrivateResponseDto;

interface GetPhoneCodeUseCase
{
    public function execute(string $countryIsoCode, string $phoneIsoCode): PhoneCodePrivateResponseDto;
}
