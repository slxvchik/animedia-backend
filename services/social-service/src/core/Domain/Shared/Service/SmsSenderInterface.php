<?php

namespace Core\Domain\Shared\Service;

use Core\Domain\Shared\ValueObject\PhoneNumber;

interface SmsSenderInterface
{
    public function send(PhoneNumber $phoneNumber, string $message): void;
}
