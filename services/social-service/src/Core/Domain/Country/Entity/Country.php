<?php

declare(strict_types=1);

namespace Core\Domain\Country\Entity;

use Core\Domain\Country\Exception\InvalidCountryIsoCodeException;
use Core\Domain\Shared\Exception\FieldRequiredException;

final class Country
{
    public private(set) string $isoCode {
        set {
            $cleanValue = trim($value);
            if (empty($cleanValue)) {
                throw new FieldRequiredException(
                    entity: self::class,
                    field: 'isoCode'
                );
            }
            if (!preg_match('/^[A-Z]{2}$/', $cleanValue)) {
                throw new InvalidCountryIsoCodeException($cleanValue);
            }
            $this->isoCode = $cleanValue;
        }
    }
    public private(set) string $name {
        set {
            $cleanValue = trim($value);
            if (empty($cleanValue)) {
                throw new FieldRequiredException(
                    entity: self::class,
                    field: 'name'
                );
            }
            $this->name = $cleanValue;
        }
    }
    public private(set) bool $active;

    public function __construct(
        string $isoCode,
        string $name,
        bool   $active = false
    ) {
        $this->isoCode = $isoCode;

        $this->name = $name;
        $this->active = $active;
    }

    public function update(
        string $name,
        bool $active
    ): void {
        $this->name = $name;
        $this->active = $active;
    }
}
