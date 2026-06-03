<?php

namespace Core\Domain\User\Repository;

use Core\Domain\User\Entity\UserProfile;

interface UserProfileCommandRepositoryInterface
{
    public function create(UserProfile $userProfile): UserProfile;
    public function update(UserProfile $userProfile): UserProfile;
    public function delete(UserProfile $userProfile): void;
}
