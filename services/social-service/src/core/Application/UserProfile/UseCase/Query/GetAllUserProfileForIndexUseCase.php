<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Query;

use Core\Application\UserProfile\DTO\UserProfileResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetAllUserProfileForIndexUseCase
{
    /**
     * @return Page<UserProfileResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
