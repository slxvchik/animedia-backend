<?php

namespace Core\Domain\PhoneCode\Repository;

use Core\Domain\PhoneCode\Entity\PhoneCode;

interface PhoneCodeQueryRepositoryInterface
{
    public function findByCountryIsoCode(string $countryIsoCode): ?PhoneCode;
    /**
     * @param string[] $countryIsoCodeList
     * @return PhoneCode[]
     */
    public function findByCountryIsoCodeList(array $countryIsoCodeList): array;
    public function existsByPhoneCode(string $phoneCode): bool;
}
