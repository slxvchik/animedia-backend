<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Repository;

use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface PhoneCodeQueryRepositoryInterface
{
    /**
     * @return PhoneCode[]
     */
    public function findAll(): array;
    public function findByCountryIsoCodeAndPhoneIsoCode(string $countryIsoCode, string $phoneIsoCode): ?PhoneCode;
    /**
     * @param string[] $countryIsoCodeList
     * @return PhoneCode[]
     */
    public function findByCountryIsoCodeList(array $countryIsoCodeList, ?bool $active = null): array;

    /**
     * @return Page<PhoneCode>
     */
    public function search(Pageable $pageable, ?bool $active, ?string $phoneCode = null): Page;
    public function existsByCountryIsoCodeAndPhoneCode(string $countryIsoCode, string $phoneCode): bool;
}
