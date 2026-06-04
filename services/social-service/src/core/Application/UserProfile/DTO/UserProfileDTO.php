<?php

namespace Code\Application\UserProfile\DTO;

use Core\Domain\UserProfile\Exception\InvalidUserProfileLanguageException;

final class UserProfileDTO
{
    /**
     * @param string $userUuid
     * @param string $username
     * @param string $usernameCode
     * @param LanguageDTO[]|null $languages
     * @param PhoneNumberDTO|null $phone
     * @param CountryDTO|null $country
     * @param string|null $imageUuid
     * @param string|null $color
     * @param string|null $description
     */
    public function __construct(
        public readonly string $userUuid,
        public readonly string $username,
        public readonly string $usernameCode,
        public ?array $languages,
        public readonly ?PhoneNumberDTO $phone,
        public readonly ?CountryDTO $country,
        public readonly ?string $imageUuid,
        public readonly ?string $color,
        public readonly ?string $description,
    ) {
        if ($this->languages !== null)
            foreach ($this->languages as $lang)
                if (!$lang instanceof LanguageDTO)
                    throw new InvalidUserProfileLanguageException();
    }
}
