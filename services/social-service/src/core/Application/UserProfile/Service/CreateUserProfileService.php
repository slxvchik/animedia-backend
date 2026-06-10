<?php

namespace Core\Application\UserProfile\Service;

use Core\Application\UserProfile\DTO\UserProfileResponseDto;
use Core\Application\UserProfile\Mapper\UserProfileApplicationMapperInterface;
use Core\Application\UserProfile\UseCase\Command\CreateUserProfileUseCase;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\UserProfile\Repository\UserProfileCommandRepositoryInterface;
use Core\Domain\UserProfile\Repository\UserProfileQueryRepositoryInterface;

final readonly class CreateUserProfileService implements CreateUserProfileUseCase
{
    public function __construct(
        private UserProfileQueryRepositoryInterface $userProfileQueryRepository,
        private UserProfileCommandRepositoryInterface $userProfileCommandRepository,
        private UserProfileApplicationMapperInterface $userProfileApplicationMapper,
        private IdentityGeneratorInterface $identityGenerator
    ) {}

    #[\Override]
    public function execute(UserProfileResponseDto $userProfileDto): UserProfileResponseDto
    {
        // TODO: check collision fields
        // TODO: save unconfirmed entity
        // TODO: send email confirmation
    }
}
