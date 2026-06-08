<?php

namespace Core\Application\UserProfile\Mapper;

use Core\Application\UserProfile\DTO\UserProfilePublicResponseDto as PublicUserProfileResponseDto;
use Core\Application\UserProfile\DTO\CreateUserProfileCommandDto;
use Core\Application\UserProfile\DTO\UserProfileResponseDto as PrivateUserProfileResponseDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileApplicationMapperInterface
{
    public function toUserProfile(CreateUserProfileCommandDto $userProfileRequestDto): UserProfile;

    public function toPrivateUserProfileResponseDto(UserProfile $userProfile, ?Country $country): PrivateUserProfileResponseDto;

    public function toPublicUserProfileResponseDto(UserProfile $userProfile, ?Country $country): PublicUserProfileResponseDto;
}
