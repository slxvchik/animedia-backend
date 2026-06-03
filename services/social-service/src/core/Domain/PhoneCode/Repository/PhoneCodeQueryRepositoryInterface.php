<?php

namespace Core\Domain\PhoneCode\Repository;

interface PhoneCodeQueryRepositoryInterface
{
    public function find(string $phoneCode);
}
