<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Service;

interface EmailSenderInterface
{
    /**
     * @param array<string, string> $vars
     */
    public function send(string $toEmail, string $template, string $localeLanguageIsoCode, array $vars = []): void;
}
