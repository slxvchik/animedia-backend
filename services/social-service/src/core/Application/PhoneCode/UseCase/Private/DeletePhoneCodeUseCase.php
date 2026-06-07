<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Private;

interface DeletePhoneCodeUseCase
{
    public function execute(string $countryIsoCode, string $phoneIsoCode): void;
}
