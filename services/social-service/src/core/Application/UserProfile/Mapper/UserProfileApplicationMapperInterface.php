<?php

namespace Core\Application\UserProfile\Mapper;

use Core\Application\UserProfile\DTO\Private\UserProfileRequestDto;
use Core\Application\UserProfile\DTO\Private\UserProfileResponseDto as PrivateUserProfileResponseDto;
use Core\Application\UserProfile\DTO\Public\UserProfileResponseDto as PublicUserProfileResponseDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileApplicationMapperInterface
{
    public function toUserProfile(UserProfileRequestDto $userProfileRequestDto): UserProfile;

    public function toPrivateUserProfileResponseDto(UserProfile $userProfile, ?Country $country): PrivateUserProfileResponseDto;

    public function toPublicUserProfileResponseDto(UserProfile $userProfile, ?Country $country): PublicUserProfileResponseDto;
}
