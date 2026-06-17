<?php

declare(strict_types=1);

namespace Core\Domain\User\Service;

use Core\Domain\User\ValueObject\UserEmail;

interface EmailTokenGenerator
{
    /**
     * @param UserEmail $email
     * @return string token which contain userUuid and email
     */
    public function generate(UserEmail $email): string;

    /**
     * @param string $token
     * @return UserEmail
     */
    public function validate(string $token): UserEmail;
}
