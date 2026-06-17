<?php

declare(strict_types=1);

namespace Core\Domain\Genre\Entity;

use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;

final class GenreTranslation
{
    public readonly string $uuid;
    public readonly string $languageIsoCode;
    public private(set) string $name {
        set => trim($value);
    }
    public private(set) ?string $description;

    /**
     * @param string $uuid
     * @param string $languageIsoCode
     * @param string $name
     * @param string|null $description
     */
    private function __construct(string $uuid, string $languageIsoCode, string $name, ?string $description)
    {
        $this->uuid = $uuid;
        $this->languageIsoCode = $languageIsoCode;
        $this->name = $name;
        $this->description = $description;
    }

    public static function fromDatabase(string $uuid, string $languageIsoCode, string $name, ?string $description): self
    {
        return new self(
            uuid: $uuid,
            languageIsoCode: $languageIsoCode,
            name: $name,
            description: $description
        );
    }

    public static function createNew(
        IdentityGeneratorInterface $identityGenerator,
        string $languageIsoCode,
        string $name,
        ?string $description
    ): self {
        $uuid = $identityGenerator->generate();
        return new self(
            uuid: $uuid,
            languageIsoCode: $languageIsoCode,
            name: $name,
            description: $description
        );
    }

    public function update(string $name, ?string $description): void
    {
        $this->name = $name;
        $this->description = $description;
    }
}
