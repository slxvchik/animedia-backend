<?php

declare(strict_types=1);

namespace Core\Application\User\UseCase\Query;

use Core\Application\User\DTO\UserResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetAllUserUseCase
{
    /**
     * @return Page<UserResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
