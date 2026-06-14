<?php

namespace Core\Domain\PhoneVerificationToken\Repository;

use Core\Domain\PhoneVerificationToken\Entity\PhoneVerificationToken;

interface PhoneVerificationTokenQueryRepository
{
    /**
     * @return PhoneVerificationToken[]
     */
    public function findByUserUuid(string $userUuid): array;

    /**
     * @return PhoneVerificationToken[]
     */
    public function findByUserUuidAndPhoneNumber(string $userUuid, string $phoneCode, string $phoneNumber): array;
}
