<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetAllUser;

use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

interface GetAllUserUseCase
{
    /**
     * @return Page<UserResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
