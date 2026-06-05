<?php

namespace Core\Application\PhoneCode\UseCase;

interface DeletePhoneCodeUseCase
{
    public function execute(string $phoneCode): void;
}
