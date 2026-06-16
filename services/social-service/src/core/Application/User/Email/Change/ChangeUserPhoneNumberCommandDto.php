<?php

declare(strict_types=1);

namespace Core\Application\User\Email\Change;

final readonly class ChangeUserPhoneNumberCommandDto
{
    public function __construct(
        public string $phoneCodeUuid,
        public string $phoneNumber
    ) {}
}
