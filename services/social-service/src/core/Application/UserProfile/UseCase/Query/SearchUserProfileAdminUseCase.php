<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Query;

use Core\Application\UserProfile\DTO\SearchUserProfilePrivateRequestDto;
use Core\Application\UserProfile\DTO\UserProfilePrivateResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;

interface SearchUserProfileAdminUseCase
{
    /**
     * @return Page<UserProfilePrivateResponseDto>
     */
    public function execute(SearchUserProfilePrivateRequestDto $searchRequestDto): Page;
}
