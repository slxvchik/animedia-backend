<?php

declare(strict_types=1);

namespace Core\Domain\Genre\Entity;

use Core\Domain\Shared\Exception\FieldRequiredException;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\Shared\SlugAlias\SlugAlias;

final class Genre
{
    public readonly string $uuid {
        set {
            $cleanValue = trim($value);
            if (empty($cleanValue)) {
                throw new FieldRequiredException(
                    entity: self::class,
                    field: 'uuid'
                );
            }
            $this->uuid = $cleanValue;
        }
    }
    public private(set) SlugAlias $slugAlias;
    public private(set) int $sortOrder {
        set {
            $this->sortOrder = $value < 0 ? 0 : $value;
        }
    }
    public private(set) bool $active;
    /**
     * @var GenreTranslation[]|null
     */
    public private(set) ?array $translationList {
        set {
            $this->translationList = $value !== null && count($value) === 0 ? null : $value;
        }
    }

    /**
     * @param string $uuid
     * @param string $slugAlias
     * @param int $sortOrder
     * @param bool $active
     * @param GenreTranslation[]|null $translationList
     */
    private function __construct(string $uuid, string $slugAlias, int $sortOrder, bool $active, ?array $translationList)
    {
        $this->uuid = $uuid;
        $this->slugAlias = new SlugAlias($slugAlias);
        $this->sortOrder = $sortOrder;
        $this->active = $active;
        $this->translationList = $translationList;
    }

    /**
     * @param string $uuid
     * @param string $slugAlias
     * @param int $sortOrder
     * @param bool $active
     * @param GenreTranslation[]|null $translationList
     * @return self
     */
    public static function fromDatabase(string $uuid, string $slugAlias, int $sortOrder, bool $active, ?array $translationList): self
    {
        return new self(
            uuid: $uuid,
            slugAlias: $slugAlias,
            sortOrder: $sortOrder,
            active: $active,
            translationList: $translationList
        );
    }

    /**
     * @param IdentityGeneratorInterface $identityGenerator
     * @param string $slugAlias
     * @param int $sortOrder
     * @param bool $active
     * @return self
     */
    public static function createNew(
        IdentityGeneratorInterface $identityGenerator,
        string $slugAlias,
        int $sortOrder,
        bool $active
    ): self {
        $uuid = $identityGenerator->generate();
        return new self(
            uuid: $uuid,
            slugAlias: $slugAlias,
            sortOrder: $sortOrder,
            active: $active,
            translationList: null
        );
    }

    /**
     * @param SlugAlias $slugAlias
     * @param int $sortOrder
     * @param bool $active
     * @param GenreTranslation[] $translationList
     * @return void
     */
    public function update(
        SlugAlias $slugAlias,
        int $sortOrder,
        bool $active,
        array $translationList
    ): void {
        $this->slugAlias = $slugAlias;
        $this->sortOrder = $sortOrder;
        $this->active = $active;
        $this->translationList = $translationList;
    }
}
