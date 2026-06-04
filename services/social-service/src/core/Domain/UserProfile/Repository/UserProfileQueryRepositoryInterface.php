<?php

namespace Core\Domain\UserProfile\Repository;


use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileQueryRepositoryInterface
{
    public function findByUserUuid(string $userUuid): ?UserProfile;
    public function findByUsernameAndUsernameCode(string $username, string $usernameCode): ?UserProfile;
    public function existsByUsernameAndUsernameCode(string $username, string $usernameCode): bool;
    public function existsByPhoneAndPhoneNumber(string $phoneCode, string $phoneNumber): bool;
}
