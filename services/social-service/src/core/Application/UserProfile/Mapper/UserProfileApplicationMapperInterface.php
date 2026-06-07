<?php

namespace Core\Application\UserProfile\Mapper;

use Core\Application\UserProfile\DTO\UserProfilePublicResponseDto as PublicUserProfileResponseDto;
use Core\Application\UserProfile\DTO\CommandUserProfileRequestDto;
use Core\Application\UserProfile\DTO\UserProfilePrivateResponseDto as PrivateUserProfileResponseDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\UserProfile\Entity\UserProfile;

interface UserProfileApplicationMapperInterface
{
    public function toUserProfile(CommandUserProfileRequestDto $userProfileRequestDto): UserProfile;

    public function toPrivateUserProfileResponseDto(UserProfile $userProfile, ?Country $country): PrivateUserProfileResponseDto;

    public function toPublicUserProfileResponseDto(UserProfile $userProfile, ?Country $country): PublicUserProfileResponseDto;
}
