<?php

namespace Code\Application\UserProfile\UseCase\Public;

use Code\Application\UserProfile\DTO\Public\UserProfileResponseDto;
use Code\Application\UserProfile\DTO\Public\UserProfileSearchRequestDto;
use Core\Domain\Shared\Pagination\Entity\Page;

interface SearchUserProfileUseCase
{
    /**
     * @return Page<UserProfileResponseDto>
     */
    public function execute(UserProfileSearchRequestDto $searchRequestDto): Page;
}
