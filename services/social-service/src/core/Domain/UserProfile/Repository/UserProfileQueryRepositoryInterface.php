<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Repository;


use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileQueryRepositoryInterface
{
    public function findByUserUuid(string $userUuid): ?UserProfile;

    /**
     * @param string[] $userUuidList
     * @return UserProfile[]
     */
    public function findByUserUuidList(array $userUuidList): array;

    public function existsByUsernameAndUsernameCode(string $username, string $usernameCode): bool;

    public function existsByPhoneAndPhoneNumber(string $phoneCode, string $phoneNumber): bool;
}
