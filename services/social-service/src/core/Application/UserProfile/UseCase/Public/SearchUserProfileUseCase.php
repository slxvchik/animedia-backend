<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\UseCase\Public;

use Core\Application\UserProfile\DTO\Public\UserProfileResponseDto;
use Core\Application\UserProfile\DTO\Public\UserProfileSearchRequestDto;
use Core\Domain\Shared\Pagination\Entity\Page;

interface SearchUserProfileUseCase
{
    /**
     * @return Page<UserProfileResponseDto>
     */
    public function execute(UserProfileSearchRequestDto $searchRequestDto): Page;
}
