<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Query;

use Core\Application\UserProfile\DTO\UserProfilePublicResponseDto;
use Core\Application\UserProfile\DTO\SearchUserProfilePublicRequestDto;
use Core\Domain\Shared\Pagination\Entity\Page;

interface SearchUserProfileUserUseCase
{
    /**
     * @return Page<UserProfilePublicResponseDto>
     */
    public function execute(SearchUserProfilePublicRequestDto $searchRequestDto): Page;
}
