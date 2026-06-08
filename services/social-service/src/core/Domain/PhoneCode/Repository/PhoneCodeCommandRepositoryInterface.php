<?php

declare(strict_types=1);

namespace Core\Domain\PhoneCode\Repository;

use Core\Domain\PhoneCode\Entity\PhoneCode;

interface PhoneCodeCommandRepositoryInterface
{
    public function create(PhoneCode $phoneCode): PhoneCode;
    public function update(PhoneCode $phoneCode): PhoneCode;
    public function delete(string $phoneCodeUuid): void;
}
