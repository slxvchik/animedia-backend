<?php

declare(strict_types=1);

namespace Core\Application\User\Query\Shared\DTO;

use Core\Application\Country\DTO\CountryResponseDto;

final readonly class PhoneNumberResponseDto
{
    public function __construct(
        public CountryResponseDto $country,
        public string $phoneCode,
        public string $phoneNumber
    ) {}
}
