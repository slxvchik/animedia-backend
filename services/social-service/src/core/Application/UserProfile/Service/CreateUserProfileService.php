<?php

namespace Core\Application\UserProfile\Service;

use Core\Application\UserProfile\DTO\UserProfilePrivateResponseDto;
use Core\Application\UserProfile\Mapper\UserProfileApplicationMapperInterface;
use Core\Application\UserProfile\UseCase\Command\CreateUserProfileUseCase;
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
    public function execute(UserProfilePrivateResponseDto $userProfileDto): UserProfilePrivateResponseDto
    {
        // TODO: Implement execute() method.
    }
}
