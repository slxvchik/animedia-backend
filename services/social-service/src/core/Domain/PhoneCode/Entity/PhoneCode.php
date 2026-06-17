<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Entity;

use Core\Domain\PhoneCode\Exception\InvalidPhoneCodeException;
use Core\Domain\Shared\IdentityGenerator\Uuid;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;

final class PhoneCode
{
    public readonly Uuid $uuid;
    public readonly string $countryIsoCode;
    public readonly string $code;
    public private(set) bool $active;

    public function __construct(
        string $uuid,
        string $countryIsoCode,
        string $phoneCode,
        bool $active = false,
    ) {
        $this->uuid = new Uuid($uuid);

        $this->assertPhoneCode($phoneCode);
        $this->code = $phoneCode;

        $this->countryIsoCode = $countryIsoCode;
        $this->active = $active;
    }

    public static function createNew(
        string $countryIsoCode,
        string $phoneCode,
        bool $active,
        IdentityGeneratorInterface $identityGenerator
    ): PhoneCode {
        $uuid = $identityGenerator->generate();
        return new self(
            uuid: $uuid,
            countryIsoCode: $countryIsoCode,
            phoneCode: $phoneCode,
            active: $active
        );
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
