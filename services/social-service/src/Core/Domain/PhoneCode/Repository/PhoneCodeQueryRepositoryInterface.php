<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Repository;

use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

interface PhoneCodeQueryRepositoryInterface
{
    /**
     * @param Pageable $pageable
     * @return Page<PhoneCode>
     */
    public function findAll(Pageable $pageable): Page;

    public function findByPhoneCodeUuid(string $phoneCodeUuid): ?PhoneCode;

    /**
     * @param string[] $phoneCodeUuidList
     * @return PhoneCode[]
     */
    public function findByPhoneCodeUuidList(array $phoneCodeUuidList): array;

    public function existsByCountryIsoCodeAndPhoneCode(string $countryIsoCode, string $phoneCode): bool;
}
