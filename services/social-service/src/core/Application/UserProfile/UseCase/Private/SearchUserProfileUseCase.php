<?php

namespace Code\Application\UserProfile\UseCase\Private;

use Code\Application\UserProfile\DTO\Private\UserProfileResponseDto;
use Code\Application\UserProfile\DTO\Private\SearchUserProfileRequestDto;
use Core\Domain\Shared\Pagination\Entity\Page;

interface SearchUserProfileUseCase
{
    /**
     * @return Page<UserProfileResponseDto>
     */
    public function execute(SearchUserProfileRequestDto $searchRequestDto): Page;
}
