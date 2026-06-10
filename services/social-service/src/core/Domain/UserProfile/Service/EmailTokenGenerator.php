<?php

namespace Core\Domain\UserProfile\Service;

interface EmailTokenGenerator
{
    public function generate(string $email): string;

    /**
     * @param string $token
     * @return string Must return email from token
     */
    public function validate(string $token): string;
}
