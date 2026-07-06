<?php

declare(strict_types=1);

namespace Core\Domain\PhoneVerificationToken\Entity;

use Core\Domain\PhoneVerificationToken\Exception\PhoneVerificationTokenAttemptsException;
use Core\Domain\PhoneVerificationToken\Exception\PhoneVerificationTokenConfirmException;
use Core\Domain\PhoneVerificationToken\Exception\PhoneVerificationTokenExpiredException;
use Core\Domain\PhoneVerificationToken\Exception\PhoneVerificationTokenInvalidCodeException;
use Core\Domain\PhoneVerificationToken\Exception\PhoneVerificationTokenUsedException;
use Core\Domain\Shared\Uuid\UuidGeneratorInterface;
use Core\Domain\Shared\Uuid\Uuid;
use Core\Domain\Shared\PhoneNumber\PhoneNumber;
use DateTimeImmutable;

final class PhoneVerificationToken
{
    public readonly Uuid $uuid;
    public private(set) string $userUuid;
    public private(set) PhoneNumber $phoneNumber;
    public private(set) int $code;
    public private(set) int $attempts;
    public private(set) bool $isUsed;
    private DateTimeImmutable $expireAt;

    private function __construct(
        string            $uuid,
        string            $userUuid,
        PhoneNumber       $phoneNumber,
        int               $code,
        int               $attempts,
        bool              $isUsed,
        DateTimeImmutable $expireAt
    ) {
        $this->uuid = new Uuid($uuid);
        $this->userUuid = $userUuid;
        $this->phoneNumber = $phoneNumber;
        $this->code = $code;
        $this->attempts = $attempts;
        $this->isUsed = $isUsed;
        $this->expireAt = $expireAt;
    }

    public static function createNew(string $userUuid, PhoneNumber $phoneNumber, UuidGeneratorInterface $uuidGenerator): PhoneVerificationToken
    {
        $code = mt_rand(100000, 999999);
        $uuid = $uuidGenerator->generate();
        return new self(
            uuid: $uuid,
            userUuid: $userUuid,
            phoneNumber: $phoneNumber,
            code: $code,
            attempts: 0,
            isUsed: false,
            expireAt: new DateTimeImmutable('now')
        );
    }

    public static function fromDb(
        string            $uuid,
        string            $userUuid,
        PhoneNumber       $phoneNumber,
        int               $code,
        int               $attempts,
        bool              $isUsed,
        DateTimeImmutable $expireAt
    ): PhoneVerificationToken {
        return new self(
            uuid: $uuid,
            userUuid: $userUuid,
            phoneNumber: $phoneNumber,
            code: $code,
            attempts: $attempts,
            isUsed: $isUsed,
            expireAt: $expireAt
        );
    }

    public function isExpired(): bool
    {
        return $this->expireAt < new DateTimeImmutable('now');
    }

    public function verify(PhoneNumber $userPhoneNumber, int $code): void
    {
        if ($this->isUsed) {
            throw new PhoneVerificationTokenUsedException();
        }

        if ($this->isExpired()) {
            throw new PhoneVerificationTokenExpiredException();
        }

        if ($this->attempts > 3) {
            throw new PhoneVerificationTokenAttemptsException();
        }

        $this->attempts++;

        if (!PhoneNumber::safeEquals($userPhoneNumber, $this->phoneNumber)) {
            throw new PhoneVerificationTokenConfirmException(
                curNumber: $userPhoneNumber,
                requestedNumber: $this->phoneNumber
            );
        }

        if ($this->code !== $code) {
            throw new PhoneVerificationTokenInvalidCodeException();
        }

        $this->isUsed = true;
    }
}
