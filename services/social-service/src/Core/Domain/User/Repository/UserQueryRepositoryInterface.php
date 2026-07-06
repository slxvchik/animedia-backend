<?php

declare(strict_types=1);

namespace Core\Domain\User\Repository;


use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;
use Core\Domain\User\Entity\User;

interface UserQueryRepositoryInterface
{
    /**
     * @param Pageable $pageable
     * @return Page<User>
     */
    public function findAll(Pageable $pageable): Page;

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

    public function existsByPhoneAndPhoneNumberExcludeUserUuid(string $phoneCodeUuid, string $phoneNumber, string $userUuid): bool;
}
