<?php

declare(strict_types=1);

namespace Core\Application\User\Update;

final readonly class UpdateUserPhoneNumberCommandDto
{
    public function __construct(
        public string $phoneCodeUuid,
        public string $phoneNumber
    ) {}
}
