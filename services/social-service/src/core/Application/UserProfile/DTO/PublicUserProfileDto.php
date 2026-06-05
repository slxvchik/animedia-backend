<?php

namespace Code\Application\UserProfile\DTO;

use Core\Application\Country\DTO\PublicCountryDto;
use Core\Application\Language\DTO\PublicLanguageDto;
use Core\Application\Shared\Exception\InvalidTypeException;

final readonly class PublicUserProfileDto
{
    /**
     * @param PublicLanguageDto[]|null $languages
     */
    public function __construct(
        public string $userUuid,
        public string $username,
        public string $usernameCode,
        public ?array $languages,
        public ?PublicCountryDto $country,
        public ?string $imageUuid,
        public ?string $color,
        public ?string $description,
    ) {
        if ($this->languages !== null)
            foreach ($this->languages as $lang)
                if (!$lang instanceof PublicLanguageDto)
                    throw new InvalidTypeException($lang, PublicLanguageDto::class);
    }
}
