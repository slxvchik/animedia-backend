<?php

namespace Core\Domain\UserProfile\Entity;

use Core\Domain\Country\Entity\Country;
use Core\Domain\Language\Entity\Language;
use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\UserProfile\Exception\InvalidPhoneNumberException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileIdException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileLanguageException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameCodeException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameException;
use Core\Domain\UserProfile\Validator\PhoneValidatorInterface;
use Core\Domain\UserProfile\ValueObject\PhoneNumber;

class UserProfile
{
    /**
     * @param string $userUuid
     * @param string $username
     * @param string $usernameCode
     * @param Country|null $country
     * @param Language[]|null $languages
     * @param PhoneNumber|null $phone
     * @param string|null $imageUuid
     * @param string|null $color
     * @param string|null $description
     */
    public function __construct(
        public readonly string $userUuid,
        private string $username,
        private string $usernameCode,
        private ?array $languages,
        private ?PhoneNumber $phone,
        public ?Country $country,
        public ?string $imageUuid,
        public ?string $color,
        public ?string $description
    ) {
        $this->assertUserUuid($this->userUuid);

        $this->username = trim($this->username);
        $this->assertUsername($this->username);

        $this->usernameCode = trim($this->usernameCode);
        $this->assertUsernameCode($this->usernameCode);

        $this->assertLanguages($this->languages);
    }

    private function assertUserUuid(string $userUuid): void
    {
        if (mb_strlen($userUuid) !== 36)
            throw new InvalidUserProfileIdException();
    }

    private function assertUsername(string $username): void
    {
        if (empty($username))
            throw new InvalidUserProfileNicknameException();
    }

    private function assertUsernameCode(string $usernameCode): void
    {
        if (empty($usernameCode))
            throw new InvalidUserProfileNicknameCodeException();
    }

    /**
     * @param Language[]|null $languages
     */
    private function assertLanguages(?array $languages): void
    {
        if (empty($languages)) return;
        foreach ($languages as $language)
            if (!($language instanceof Language))
                throw new InvalidUserProfileLanguageException();
    }

    public function getUsername(): string
    {
        return $this->username;
    }

    public function setUsername(string $username): void
    {
        $cleanedUsername = trim($username);
        $this->assertUsername($cleanedUsername);
        $this->username = $cleanedUsername;
    }

    public function getUsernameCode(): string
    {
        return $this->usernameCode;
    }

    public function setUsernameCode(string $usernameCode): void
    {
        $cleanedUsernameCode = trim($usernameCode);
        $this->assertUsernameCode($cleanedUsernameCode);
        $this->usernameCode = $cleanedUsernameCode;
    }

    public function getLanguages(): ?array
    {
        return $this->languages;
    }

    public function setLanguages(?array $languages): void
    {
        $this->assertLanguages($languages);
        $this->languages = $languages;
    }

    public function getPhone(): ?PhoneNumber
    {
        return $this->phone;
    }

    public function setPhone(
        ?PhoneCode $code,
        ?string $number,
        PhoneValidatorInterface $validator
    ): void {

        if ($code === null || $number === null || trim($number) === '') {
            $this->phone = null;
            return;
        }

        $cleanedNumber = trim($number);

        if (!$validator->isValid($code, $cleanedNumber))
            throw new InvalidPhoneNumberException($cleanedNumber);

        $this->phone = new PhoneNumber($code, $number, false);
    }
}
