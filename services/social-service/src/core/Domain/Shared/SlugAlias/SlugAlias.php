<?php

declare(strict_types=1);

namespace Core\Domain\Shared\SlugAlias;

final class SlugAlias
{
    private const string PATTERN = "/^[a-z]+(-[a-z]+)*$/";
    public private(set) string $value {
        set {
            if (!preg_match(self::PATTERN, $value)) {
                throw new InvalidSlugAliasException($value);
            }
            $this->value = $value;
        }
    }

    public function __construct(string $value)
    {
        $this->value = $value;
    }
}
