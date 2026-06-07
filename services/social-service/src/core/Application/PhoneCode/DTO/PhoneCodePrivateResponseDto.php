<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\DTO;

use Core\Application\Country\DTO\CountryDto;

final readonly class PhoneCodePrivateResponseDto
{
    public function __construct(
        public string $phoneCode,
        public bool $isActive,
        public ?CountryDto $country = null
    ) {}
}
