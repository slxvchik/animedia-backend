<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO;

use Core\Application\Country\DTO\CountryPublicResponseDto;
use Core\Application\Shared\Exception\InvalidTypeException;

final readonly class UserProfilePublicResponseDto
{
    /**
     * @param UserProfilePublicResponseDto[]|null $languages
     */
    public function __construct(
        public string                    $username,
        public string                    $usernameCode,
        public ?array                    $languages,
        public ?CountryPublicResponseDto $country,
        public ?string                   $imageUuid,
        public ?string                   $color,
        public ?string                   $description,
    ) {
        if ($this->languages !== null) {
            foreach ($this->languages as $lang) {
                if (!$lang instanceof UserProfilePublicResponseDto) {
                    throw new InvalidTypeException($lang, UserProfilePublicResponseDto::class);
                }
            }
        }
    }
}
