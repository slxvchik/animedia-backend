<?php

namespace Core\Application\PhoneCode\UseCase\Private;

interface DeletePhoneCodeUseCase
{
    public function execute(string $phoneCode): void;
}
