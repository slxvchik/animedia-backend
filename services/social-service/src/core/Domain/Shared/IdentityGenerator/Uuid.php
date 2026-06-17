<?php

declare(strict_types=1);

namespace Core\Domain\Shared\IdentityGenerator;

final class Uuid
{
    private const string PATTERN = '/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i';
    public private(set) string $value {
        set {
            if (!preg_match(self::PATTERN, $value)) {
                throw new InvalidUuidException($value);
            }
            $this->value = $value;
        }
    }

    public function __construct(string $value)
    {
        $this->value = $value;
    }
}
