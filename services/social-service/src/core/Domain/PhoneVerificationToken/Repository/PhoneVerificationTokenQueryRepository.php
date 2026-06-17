<?php

declare(strict_types=1);

namespace Core\Domain\PhoneVerificationToken\Repository;

use Core\Domain\PhoneVerificationToken\Entity\PhoneVerificationToken;
use DateTimeImmutable;

interface PhoneVerificationTokenQueryRepository
{
    public function findLastTokenByUserUuid(string $userUuid): ?PhoneVerificationToken;

    public function countRecentTokensByUserUuid(string $userUuid, DateTimeImmutable $since): int;
}
