<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Entity;

use Core\Domain\PhoneCode\Exception\InvalidPhoneCodeException;
use Core\Domain\PhoneCode\Exception\InvalidPhoneCodeUuidException;

final class PhoneCode
{
    public readonly string $uuid;
    public readonly string $countryIsoCode;
    public readonly string $code;
    public private(set) bool $active;

    public function __construct(
        ?string $uuid,
        string $countryIsoCode,
        string $phoneCode,
        bool $active = false,
    ) {
        $this->assertPhoneCodeUuid($uuid);
        $this->uuid = $uuid;

        $this->assertPhoneCode($phoneCode);
        $this->code = $phoneCode;

        $this->countryIsoCode = $countryIsoCode;
        $this->active = $active;
    }

    private function assertPhoneCodeUuid(?string $phoneCodeUuid): void
    {
        if ($phoneCodeUuid === null) {
            return;
        }
        if (!preg_match('/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i', $phoneCodeUuid)) {
            throw new InvalidPhoneCodeUuidException();
        }
    }

    private function assertPhoneCode(string $phoneCode): void
    {
        if (!preg_match('/^+[0-9]{1,4}$/', $phoneCode)) {
            throw new InvalidPhoneCodeException($phoneCode);
        }
    }

    public function update(
        bool $active = false
    ): void {
        $this->active = $active;
    }
}
