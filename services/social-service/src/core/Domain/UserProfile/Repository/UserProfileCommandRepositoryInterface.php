<?php

namespace Core\Domain\UserProfile\Repository;

use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileCommandRepositoryInterface
{
    public function create(UserProfile $userProfile): UserProfile;
    public function update(UserProfile $userProfile): UserProfile;
    public function delete(UserProfile $userProfile): void;
}
