<?php

namespace Core\Domain\PhoneVerificationToken\Entity;

use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\Shared\ValueObject\PhoneNumber;
use DateTimeImmutable;

final class PhoneVerificationToken
{
    public readonly string $uuid;
    public private(set) string $userUuid;
    public private(set) PhoneNumber $phoneNumber;
    public private(set) int $code;
    public private(set) DateTimeImmutable $expireAt;

    private function __construct(
        string $uuid,
        string $userUuid,
        PhoneNumber $phoneNumber,
        int $code,
        DateTimeImmutable $expireAt
    ) {
        $this->uuid = $uuid;
        $this->userUuid = $userUuid;
        $this->phoneNumber = $phoneNumber;
        $this->code = $code;
        $this->expireAt = $expireAt;
    }

    public static function createNew(string $userUuid, PhoneNumber $phoneNumber, IdentityGeneratorInterface $identityGenerator): PhoneVerificationToken
    {
        $code = mt_rand(100000, 999999);
        $uuid = $identityGenerator->generate();
        return new self(
            uuid: $uuid,
            userUuid: $userUuid,
            phoneNumber: $phoneNumber,
            code: $code,
            expireAt: new DateTimeImmutable('now')
        );
    }

    public static function fromDb(
        string $uuid,
        string $userUuid,
        PhoneNumber $phoneNumber,
        int $code,
        DateTimeImmutable $expireAt
    ): PhoneVerificationToken {
        return new self(
            uuid: $uuid,
            userUuid: $userUuid,
            phoneNumber: $phoneNumber,
            code: $code,
            expireAt: $expireAt
        );
    }
}
