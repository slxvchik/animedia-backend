<?php

namespace Core\Application\PhoneCode\DTO\Private;

use Core\Application\Country\DTO\Private\CountryDto;

final readonly class PhoneCodeResponseDto
{
    public function __construct(
        public CountryDto $country,
        public string     $phoneCode,
        public bool       $isActive
    ) {}
}
