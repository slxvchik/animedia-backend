<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Service;

use Core\Domain\Shared\PhoneNumber\PhoneNumber;

interface SmsSenderInterface
{
    /**
     * @param array<string, string> $vars
     */
    public function send(PhoneNumber $toPhone, string $template, string $localeLanguageIsoCode, array $vars = []): void;
}
