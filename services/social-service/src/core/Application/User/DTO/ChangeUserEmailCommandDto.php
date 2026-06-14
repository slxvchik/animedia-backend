<?php

namespace Core\Application\User\DTO;

final readonly class ChangeUserEmailCommandDto
{
    public function __construct(
        public string $userUuid,
        public string $newEmail
    ) {}
}
