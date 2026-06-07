<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Private;

use Core\Application\UserProfile\DTO\Private\SearchUserProfileRequestDto;
use Core\Application\UserProfile\DTO\Private\UserProfileResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;

interface SearchUserProfileUseCase
{
    /**
     * @return Page<UserProfileResponseDto>
     */
    public function execute(SearchUserProfileRequestDto $searchRequestDto): Page;
}
