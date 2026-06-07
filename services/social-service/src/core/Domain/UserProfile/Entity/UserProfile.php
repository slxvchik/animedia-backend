<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Entity;

use Core\Domain\UserProfile\Exception\InvalidUserProfileEmailException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileIdException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileLanguageException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameCodeException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameException;
use Core\Domain\UserProfile\ValueObject\PhoneNumber;

final class UserProfile
{
    public readonly string $userUuid;
    public private(set) string $username
    {
        set {
            $this->username = trim($value);
            $this->assertUsername($this->username);
        }
    }
    public private(set) string $usernameCode
    {
        set {
            $this->usernameCode = trim($value);
            $this->assertUsernameCode($this->usernameCode);
        }
    }
    public private(set) string $email
    {
        set {
            $this->email = trim($value);
            $this->assertEmail($this->email);
        }
    }
    public private(set) ?string $firstName;
    public private(set) ?string $lastName;
    public private(set) ?string $middleName;
    /**
     * @var string[]|null
     */
    public private(set) ?array $languageIsoCodeList
    {
        set {
            $this->languageIsoCodeList = $value;
            $this->assertLanguageIsoCodes($this->languageIsoCodeList);
        }
    }
    public private(set) ?PhoneNumber $phone;
    public private(set) ?string $countryIsoCode;
    public private(set) ?string $imageUuid;
    public private(set) ?string $color;
    public private(set) ?string $description;
    public private(set) bool $emailConfirmed;
    /**
     * @param string[]|null $languageIsoCodeList
     */
    public function __construct(
        string       $userUuid,
        string       $username,
        string       $usernameCode,
        string       $email,
        ?string      $firstName,
        ?string      $lastName,
        ?string      $middleName,
        ?array       $languageIsoCodeList,
        ?PhoneNumber $phone,
        ?string      $countryIsoCode,
        ?string      $imageUuid,
        ?string      $color,
        ?string      $description,
        bool         $emailConfirmed = false
    ) {
        $this->assertUserUuid($userUuid);
        $this->userUuid = $userUuid;

        $this->username = $username;
        $this->usernameCode = $usernameCode;
        $this->email = $email;
        $this->languageIsoCodeList = $languageIsoCodeList;
        $this->firstName = $firstName;
        $this->lastName = $lastName;
        $this->middleName = $middleName;
        $this->phone = $phone;
        $this->countryIsoCode = $countryIsoCode;
        $this->imageUuid = $imageUuid;
        $this->color = $color;
        $this->description = $description;
        $this->emailConfirmed = $emailConfirmed;
    }

    private function assertUserUuid(string $userUuid): void
    {
        if (!preg_match('/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i', $userUuid)) {
            throw new InvalidUserProfileIdException();
        }
    }

    private function assertUsername(string $username): void
    {
        if (empty($username) || mb_strlen($username) > 32) {
            throw new InvalidUserProfileNicknameException();
        }
    }

    private function assertUsernameCode(string $usernameCode): void
    {
        if (empty($usernameCode) || mb_strlen($usernameCode) > 10) {
            throw new InvalidUserProfileNicknameCodeException();
        }
    }

    private function assertEmail(string $email): void
    {
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            throw new InvalidUserProfileEmailException($email);
        }
    }

    /**
     * @param string[]|null $languageIsoCodeList
     */
    private function assertLanguageIsoCodes(?array $languageIsoCodeList): void
    {
        if ($languageIsoCodeList === null) {
            return;
        }
        foreach ($languageIsoCodeList as $language) {
            if (!is_string($language)) {
                throw new InvalidUserProfileLanguageException();
            }
        }
    }

    public function update(): void
    {

    }
}
