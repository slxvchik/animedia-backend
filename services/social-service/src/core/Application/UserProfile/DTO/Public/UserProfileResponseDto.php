<?php

declare(strict_types=1);

namespace Core\Application\UserProfile\DTO\Public;

use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Application\Language\DTO\Public\LanguageResponseDto;
use Core\Application\Shared\Exception\InvalidTypeException;

final readonly class UserProfileResponseDto
{
    /**
     * @param UserProfileResponseDto[]|null $languages
     */
    public function __construct(
        public string                  $username,
        public string                  $usernameCode,
        public ?array                  $languages,
        public ?UserProfileResponseDto $country,
        public ?string                 $imageUuid,
        public ?string                 $color,
        public ?string                 $description,
    ) {
        if ($this->languages !== null)
            foreach ($this->languages as $lang)
                if (!$lang instanceof UserProfileResponseDto)
                    throw new InvalidTypeException($lang, UserProfileResponseDto::class);
    }
}
