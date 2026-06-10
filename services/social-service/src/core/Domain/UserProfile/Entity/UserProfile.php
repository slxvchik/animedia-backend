<?php

declare(strict_types=1);

namespace Core\Domain\UserProfile\Entity;

use Core\Domain\Shared\Eventable\Eventable;
use Core\Domain\UserProfile\Events\SendUserEmailConfirmEvent;
use Core\Domain\UserProfile\Exception\InvalidUserProfileEmailException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileIdException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileLanguageException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameCodeException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameException;
use Core\Domain\UserProfile\ValueObject\PhoneNumber;
use DateTimeImmutable;

final class UserProfile
{
    use Eventable;

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
    public private(set) bool $emailConfirmed;
    public private(set) ?PhoneNumber $phone;
    public private(set) bool $phoneConfirmed;
    public private(set) DateTimeImmutable $createdAt;
    public private(set) DateTimeImmutable $updatedAt;
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
    public private(set) ?string $countryIsoCode;
    public private(set) ?string $imageUuid;
    public private(set) ?string $color;
    public private(set) ?string $description;

    /**
     * @param string[]|null $languageIsoCodeList
     */
    private function __construct(
        string            $userUuid,
        string            $username,
        string            $usernameCode,
        string            $email,
        bool              $emailConfirmed,
        DateTimeImmutable $createdAt,
        DateTimeImmutable $updatedAt,
        ?PhoneNumber      $phone = null,
        bool              $phoneConfirmed = false,
        ?string           $firstName = null,
        ?string           $lastName = null,
        ?string           $middleName = null,
        ?array            $languageIsoCodeList = null,
        ?string           $countryIsoCode = null,
        ?string           $imageUuid = null,
        ?string           $color = null,
        ?string           $description = null
    ) {
        $this->assertUserUuid($userUuid);
        $this->userUuid = $userUuid;

        // required fields
        $this->username = $username;
        $this->usernameCode = $usernameCode;
        $this->email = $email;
        $this->emailConfirmed = $emailConfirmed;
        $this->createdAt = $createdAt;
        $this->updatedAt = $updatedAt;

        $this->phone = $phone;
        $this->phoneConfirmed = $phoneConfirmed;
        $this->languageIsoCodeList = $languageIsoCodeList;
        $this->firstName = $firstName;
        $this->lastName = $lastName;
        $this->middleName = $middleName;
        $this->countryIsoCode = $countryIsoCode;
        $this->imageUuid = $imageUuid;
        $this->color = $color;
        $this->description = $description;
    }

    public static function createNew(
        string $userUuid,
        string $username,
        string $usernameCode,
        string $email,
        bool   $emailConfirmed
    ): UserProfile {
        $newProfile = new self(
            userUuid: $userUuid,
            username: $username,
            usernameCode: $usernameCode,
            email: $email,
            emailConfirmed: $emailConfirmed,
            createdAt: new DateTimeImmutable('now'),
            updatedAt: new DateTimeImmutable('now')
        );

        if (!$emailConfirmed) {
            $newProfile->recordEvent(
                new SendUserEmailConfirmEvent(
                    $email
                )
            );
        }

        return $newProfile;
    }

    public static function fromDatabase(
        string            $userUuid,
        string            $username,
        string            $usernameCode,
        string            $email,
        bool              $emailConfirmed,
        DateTimeImmutable $createdAt,
        DateTimeImmutable $updatedAt,
        ?PhoneNumber      $phone = null,
        bool              $phoneConfirmed = false,
        ?string           $firstName = null,
        ?string           $lastName = null,
        ?string           $middleName = null,
        ?array            $languageIsoCodeList = null,
        ?string           $countryIsoCode = null,
        ?string           $imageUuid = null,
        ?string           $color = null,
        ?string           $description = null
    ): UserProfile {
        return new self(
            userUuid: $userUuid,
            username: $username,
            usernameCode: $usernameCode,
            email: $email,
            emailConfirmed: $emailConfirmed,
            createdAt: $createdAt,
            updatedAt: $updatedAt,
            phone: $phone,
            phoneConfirmed: $phoneConfirmed,
            firstName: $firstName,
            lastName: $lastName,
            middleName: $middleName,
            languageIsoCodeList: $languageIsoCodeList,
            countryIsoCode: $countryIsoCode,
            imageUuid: $imageUuid,
            color: $color,
            description: $description
        );
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

    /**
     * @param string[]|null $languageIsoCodeList
     */
    public function update(
        ?string $firstName,
        ?string $lastName,
        ?string $middleName,
        ?array  $languageIsoCodeList,
        ?string $countryIsoCode,
        ?string $imageUuid,
        ?string $color,
        ?string $description
    ): void {
        $this->assertLanguageIsoCodes($languageIsoCodeList);

        $this->firstName = $firstName;
        $this->lastName = $lastName;
        $this->middleName = $middleName;
        $this->languageIsoCodeList = $languageIsoCodeList;
        $this->countryIsoCode = $countryIsoCode;
        $this->imageUuid = $imageUuid;
        $this->color = $color;
        $this->description = $description;

        $this->updatedAt = new DateTimeImmutable('now');
    }

    public function updateEmail(string $newEmail): void
    {
        $this->assertEmail($newEmail);

        if ($this->email === $newEmail) {
            return;
        }

        $this->email = $newEmail;
        $this->emailConfirmed = false;
        $this->updatedAt = new DateTimeImmutable('now');

        $this->recordEvent(
            new SendUserEmailConfirmEvent(
                $this->email,
                'test'
            )
        );
    }

    public function confirmEmail(string $token): void
    {
        if ($this->emailConfirmed) {
            return;
        }

        $this->emailConfirmed = true;
        $this->updatedAt = new DateTimeImmutable('now');
    }

    //TODO: generate event - confirm phone
    public function updatePhoneNumber(?PhoneNumber $phoneNumber): void
    {

    }

    public function confirmPhoneNumber(string $code): void
    {

    }
}
