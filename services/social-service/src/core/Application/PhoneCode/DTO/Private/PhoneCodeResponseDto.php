<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\DTO\Private;

use Core\Application\Country\DTO\Private\CountryDto;

final readonly class PhoneCodeResponseDto
{
    public function __construct(
        public string $phoneCode,
        public bool $isActive,
        public ?CountryDto $country = null
    ) {}
}
