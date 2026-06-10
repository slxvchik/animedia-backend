<?php

namespace Core\Application\UserProfile\DTO;

final readonly class ChangeUserProfileEmailCommandDto
{
    public function __construct(
        public string $userUuid,
        public string $newEmail
    ) {}
}
