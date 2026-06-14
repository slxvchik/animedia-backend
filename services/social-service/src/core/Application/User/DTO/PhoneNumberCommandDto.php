<?php

declare(strict_types=1);

namespace Core\Application\User\DTO;

final readonly class PhoneNumberCommandDto
{
    public function __construct(
        public string $phoneCodeUuid,
        public string $phoneNumber
    ) {}
}
