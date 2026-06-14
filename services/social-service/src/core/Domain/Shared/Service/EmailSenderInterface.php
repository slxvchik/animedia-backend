<?php

namespace Core\Domain\Shared\Service;

interface EmailSenderInterface
{
    /**
     * @param array<string, string> $vars
     */
    public function send(string $toEmail, string $template, string $locale, array $vars = []): void;
}
