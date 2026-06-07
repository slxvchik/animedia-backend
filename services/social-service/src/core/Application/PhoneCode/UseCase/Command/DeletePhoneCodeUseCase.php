<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Command;

interface DeletePhoneCodeUseCase
{
    public function execute(string $countryIsoCode, string $phoneIsoCode): void;
}
