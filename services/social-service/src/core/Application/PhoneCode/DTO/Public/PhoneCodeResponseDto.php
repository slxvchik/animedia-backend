<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\DTO\Public;

use Core\Application\Country\DTO\Public\CountryResponseDto;

final readonly class PhoneCodeResponseDto
{
    public function __construct(
        public string $phoneCode,
        public CountryResponseDto $country
    ) {}
}
