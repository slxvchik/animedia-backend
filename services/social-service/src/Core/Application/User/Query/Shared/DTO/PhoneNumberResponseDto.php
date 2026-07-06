<?php

declare(strict_types=1);

namespace Core\Application\User\Query\Shared\DTO;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;

final readonly class PhoneNumberResponseDto
{
    public function __construct(
        public PhoneCodeResponseDto $code,
        public string               $number
    ) {}
}
