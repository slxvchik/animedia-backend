<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Entity;

use Core\Domain\PhoneCode\Exception\InvalidPhoneCodeException;
use Core\Domain\Shared\Exception\FieldRequiredException;
use Core\Domain\Shared\Uuid\Uuid;
use Core\Domain\Shared\Uuid\UuidGeneratorInterface;

final class PhoneCode
{
    public readonly Uuid $uuid;
    public private(set) string $countryIsoCode {
        set {
            $cleanValue = trim($value);
            if (empty($cleanValue)) {
                throw new FieldRequiredException(
                    entity: self::class,
                    field: 'countryIsoCode'
                );
            }
            $this->countryIsoCode = $cleanValue;
        }
    }
    public private(set) string $code {
        set {
            $cleanValue = trim($value);
            if (!preg_match('/^+[0-9]{1,4}$/', $cleanValue)) {
                throw new InvalidPhoneCodeException($cleanValue);
            }
            $this->code = $cleanValue;
        }
    }
    public private(set) bool $active;

    public function __construct(
        string $uuid,
        string $countryIsoCode,
        string $phoneCode,
        bool $active = false,
    ) {
        $this->uuid = new Uuid($uuid);
        $this->code = $phoneCode;
        $this->countryIsoCode = $countryIsoCode;
        $this->active = $active;
    }

    public static function createNew(
        string $countryIsoCode,
        string $phoneCode,
        bool $active,
        UuidGeneratorInterface $uuidGenerator
    ): PhoneCode {
        $uuid = $uuidGenerator->generate();
        return new self(
            uuid: $uuid,
            countryIsoCode: $countryIsoCode,
            phoneCode: $phoneCode,
            active: $active
        );
    }

    public function update(
        bool $active = false
    ): void {
        $this->active = $active;
    }
}
