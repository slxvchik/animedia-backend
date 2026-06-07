<?php

namespace Core\Application\UserProfile\Service\Private;

use Core\Application\UserProfile\DTO\Private\UserProfileResponseDto;
use Core\Application\UserProfile\Mapper\UserProfileApplicationMapperInterface;
use Core\Application\UserProfile\UseCase\Private\CreateUserProfileUseCase;
use Core\Domain\UserProfile\Repository\UserProfileCommandRepositoryInterface;
use Core\Domain\UserProfile\Repository\UserProfileQueryRepositoryInterface;

final readonly class CreateUserProfileService implements CreateUserProfileUseCase
{
    public function __construct(
        private UserProfileQueryRepositoryInterface $userProfileQueryRepository,
        private UserProfileCommandRepositoryInterface $userProfileCommandRepository,
        private UserProfileApplicationMapperInterface $userProfileApplicationMapper
    ) {}

    #[\Override]
    public function execute(UserProfileResponseDto $userProfileDto): UserProfileResponseDto
    {
        // TODO: Implement execute() method.
    }
}
