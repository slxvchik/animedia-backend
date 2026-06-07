<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\DTO\Private;

final readonly class PhoneCodeRequestDto
{
    public function __construct(
        public string $countryIsoCode,
        public string $phoneCode,
        public bool $isActive
    ) {}
}
