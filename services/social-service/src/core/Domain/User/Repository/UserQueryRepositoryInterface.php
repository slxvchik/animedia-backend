<?php

declare(strict_types=1);

namespace Core\Domain\User\Repository;


use Core\Domain\User\Entity\User;

interface UserQueryRepositoryInterface
{
    public function findByUserUuid(string $userUuid): ?User;

    /**
     * @param string[] $userUuidList
     * @return User[]
     */
    public function findByUserUuidList(array $userUuidList): array;

    public function existsByUsernameAndUsernameCode(string $username, string $usernameCode): bool;

    public function existsByUsernameAndUsernameCodeExcludeUserUuid(string $username, string $usernameCode, string $userUuid): bool;

    public function existsByEmail(string $email): bool;

    public function existsByEmailExcludeUserUuid(string $email, string $userUuid): bool;

    public function existsByPhoneAndPhoneNumber(string $phoneCode, string $phoneNumber): bool;

    public function existsByPhoneAndPhoneNumberExcludeUserUuid(string $phoneCode, string $phoneNumber, string $userUuid): bool;
}
