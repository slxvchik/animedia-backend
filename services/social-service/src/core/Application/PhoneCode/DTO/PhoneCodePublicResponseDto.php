<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\DTO;

use Core\Application\Country\DTO\CountryPublicResponseDto;

final readonly class PhoneCodePublicResponseDto
{
    public function __construct(
        public string $phoneCode,
        public CountryPublicResponseDto $country
    ) {}
}
