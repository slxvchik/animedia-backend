<?php

namespace Core\Domain\Shared\Service;

use Core\Domain\Shared\ValueObject\PhoneNumber;

interface SmsSenderInterface
{
    /**
     * @param array<string, string> $vars
     */
    public function send(PhoneNumber $toPhone, string $template, string $localeLanguageIsoCode, array $vars = []): void;
}
