<?php

namespace Core\Domain\UserProfile\Repository;


use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\UserProfile\Entity\UserProfile;
use Core\Domain\UserProfile\ValueObject\SearchUserProfile;

interface UserProfileQueryRepositoryInterface
{
    public function findByUserUuid(string $userUuid): ?UserProfile;
    public function findByUsernameAndUsernameCode(string $username, string $usernameCode): ?UserProfile;
    /**
     * @param string[] $userUuidList
     * @return UserProfile[]
     */
    public function findByUserUuidList(array $userUuidList): array;

    /**
     * @return Page<UserProfile>
     */
    public function search(?SearchUserProfile $searchUserProfile): Page;
    public function existsByUsernameAndUsernameCode(string $username, string $usernameCode): bool;
    public function existsByPhoneAndPhoneNumber(string $phoneCode, string $phoneNumber): bool;
}
