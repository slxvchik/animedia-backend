<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO;

final readonly class PhoneNumberDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $phoneCode,
        public string $phoneNumber
    ) {}
}
