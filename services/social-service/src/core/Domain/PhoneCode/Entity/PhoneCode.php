<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Entity;

use Core\Domain\PhoneCode\Exception\InvalidPhoneCodeException;

final class PhoneCode
{
    public readonly string $countryIsoCode;
    public private(set) string $code;
    public private(set) bool $active;

    public function __construct(
        string $countryIsoCode,
        string $phoneCode,
        bool $active = false,
    ) {
        $this->assertPhoneCode($phoneCode);
        $this->code = $phoneCode;

        $this->countryIsoCode = $countryIsoCode;
        $this->active = $active;
    }

    private function assertPhoneCode(string $phoneCode): void
    {
        if (!preg_match('/^+[0-9]{1,4}$/', $phoneCode)) {
            throw new InvalidPhoneCodeException($phoneCode);
        }
    }

    public function update(
        string $phoneCode,
        bool $active = false
    ): void {
        $this->code = $phoneCode;
        $this->active = $active;
    }
}
