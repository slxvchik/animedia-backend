<?php

namespace Code\Application\UserProfile\DTO;

final readonly class PhoneNumberDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $phoneCode,
        public string $phoneNumber
    ) {}
}
