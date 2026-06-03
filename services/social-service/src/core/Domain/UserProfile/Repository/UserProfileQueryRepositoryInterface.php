<?php

namespace Core\Domain\User\Repository;


use Core\Domain\User\Entity\UserProfile;

interface UserProfileQueryRepositoryInterface
{
    public function find(string $userUuid): UserProfile;
    public function findByUsername(string $username, string $usernameCode): UserProfile;
    public function existsByUsername(string $username, string $usernameCode): bool;
    public function existsByPhone(string $phoneCode, string $phoneNumber): bool;
}
