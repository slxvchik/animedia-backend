<?php

declare(strict_types=1);

namespace Core\Domain\User\Repository;

use Core\Domain\User\Entity\User;

interface UserCommandRepositoryInterface
{
    public function create(User $user): string;
    public function update(User $user): void;
    public function delete(User $user): void;
}
