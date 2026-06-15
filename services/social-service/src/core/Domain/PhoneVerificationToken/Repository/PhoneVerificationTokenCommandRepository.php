<?php

namespace Core\Domain\PhoneVerificationToken\Repository;

use Core\Domain\PhoneVerificationToken\Entity\PhoneVerificationToken;
use DateTimeImmutable;

interface PhoneVerificationTokenCommandRepository
{
    public function create(PhoneVerificationToken $phoneVerificationToken): void;

    public function update(PhoneVerificationToken $phoneVerificationToken): void;

    public function delete(string $uuid): void;

    public function deleteExpired(): void;
}
