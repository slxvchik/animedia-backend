<?php

namespace Core\Application\PhoneCode\DTO;

use Core\Application\Country\DTO\CountryDto;

final readonly class PhoneCodeDto
{
    public function __construct(
        public CountryDto $country,
        public string $phoneCode,
        public bool $isActive
    ) {}
}
