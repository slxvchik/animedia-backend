<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\DTO;

use Core\Application\Country\DTO\CountryResponseDto;

final readonly class PhoneCodeResponseDto
{
    public function __construct(
        public string $uuid,
        public string $phoneCode,
        public bool $isActive,
        public ?CountryResponseDto $country = null
    ) {}
}
