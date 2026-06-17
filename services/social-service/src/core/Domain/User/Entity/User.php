<?php

declare(strict_types=1);

namespace Core\Domain\User\Entity;

use Core\Domain\Shared\Event\Eventable;
use Core\Domain\Shared\IdentityGenerator\Uuid;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\Shared\PhoneNumber\PhoneNumber;
use Core\Domain\User\Event\UserEmailChangedEvent;
use Core\Domain\User\Event\UserPhoneChangedEvent;
use Core\Domain\User\Exception\UserEmailAlreadyConfirmedException;
use Core\Domain\User\Exception\UserInvalidEmailException;
use Core\Domain\User\Exception\UserInvalidLanguageException;
use Core\Domain\User\Exception\UserInvalidUsernameCodeException;
use Core\Domain\User\Exception\UserInvalidUsernameException;
use DateTimeImmutable;

final class User
{
    use Eventable;

    public readonly Uuid $uuid;
    public private(set) string $username {
        set {
            if (empty($value) || mb_strlen($value) > 32) {
                throw new UserInvalidUsernameException();
            }
            $this->username = trim($value);
        }
    }
    public private(set) string $usernameCode {
        set {
            if (empty($value) || mb_strlen($value) > 10) {
                throw new UserInvalidUsernameCodeException();
            }
            $this->usernameCode = trim($value);
        }
    }
    public private(set) string $email {
        set {
            if (!filter_var($value, FILTER_VALIDATE_EMAIL)) {
                throw new UserInvalidEmailException($value);
            }
            $this->email = trim($value);
        }
    }
    public private(set) bool $emailConfirmed;
    public private(set) string $localeLanguageIsoCode;
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
    public private(set) ?array $languageIsoCodeList {
        set {
            $this->languageIsoCodeList = $value;
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
        string            $uuid,
        string            $username,
        string            $usernameCode,
        string            $email,
        bool              $emailConfirmed,
        DateTimeImmutable $createdAt,
        DateTimeImmutable $updatedAt,
        string            $localeLanguageIsoCode,
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
        $this->uuid = new Uuid($uuid);

        // required fields
        $this->username = $username;
        $this->usernameCode = $usernameCode;
        $this->email = $email;
        $this->emailConfirmed = $emailConfirmed;
        $this->createdAt = $createdAt;
        $this->updatedAt = $updatedAt;
        $this->localeLanguageIsoCode = $localeLanguageIsoCode;

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
        string                     $username,
        string                     $usernameCode,
        string                     $email,
        bool                       $emailConfirmed,
        string                     $localeLanguageIsoCode,
        IdentityGeneratorInterface $identityGenerator
    ): User {
        $uuid = $identityGenerator->generate();
        return new self(
            uuid: $uuid,
            username: $username,
            usernameCode: $usernameCode,
            email: $email,
            emailConfirmed: $emailConfirmed,
            createdAt: new DateTimeImmutable('now'),
            updatedAt: new DateTimeImmutable('now'),
            localeLanguageIsoCode: $localeLanguageIsoCode
        );
    }

    public static function fromDatabase(
        string            $uuid,
        string            $username,
        string            $usernameCode,
        string            $email,
        bool              $emailConfirmed,
        DateTimeImmutable $createdAt,
        DateTimeImmutable $updatedAt,
        string            $localeLanguageIsoCode,
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
    ): User {
        return new self(
            uuid: $uuid,
            username: $username,
            usernameCode: $usernameCode,
            email: $email,
            emailConfirmed: $emailConfirmed,
            createdAt: $createdAt,
            updatedAt: $updatedAt,
            localeLanguageIsoCode: $localeLanguageIsoCode,
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

    /**
     * @param string[]|null $languageIsoCodeList
     */
    public function update(
        string  $username,
        string  $usernameCode,
        string  $localeLanguageIsoCode,
        ?string $firstName,
        ?string $lastName,
        ?string $middleName,
        ?array  $languageIsoCodeList,
        ?string $countryIsoCode,
        ?string $imageUuid,
        ?string $color,
        ?string $description
    ): void {
        $this->username = $username;
        $this->usernameCode = $usernameCode;

        $this->localeLanguageIsoCode = $localeLanguageIsoCode;
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

    public function updateEmail(string $newEmail, bool $emailConfirmed = false): void
    {
        if ($this->email === $newEmail) {
            return;
        }

        $this->email = $newEmail;
        $this->emailConfirmed = $emailConfirmed;
        $this->updatedAt = new DateTimeImmutable('now');

        if ($this->emailConfirmed) {
            return;
        }

        $this->initiateEmailConfirmation();
    }

    public function initiateEmailConfirmation(): void
    {
        if ($this->emailConfirmed) {
            throw new UserEmailAlreadyConfirmedException($this->email);
        }

        $this->recordEvent(
            new UserEmailChangedEvent(
                userUuid: $this->uuid->value
            )
        );
    }

    public function confirmEmail(): void
    {
        if ($this->emailConfirmed) {
            return;
        }

        $this->emailConfirmed = true;
        $this->updatedAt = new DateTimeImmutable('now');
    }

    public function updatePhoneNumber(?PhoneNumber $newPhoneNumber, bool $phoneConfirmed = false): void
    {
        if (PhoneNumber::safeEquals($this->phone, $newPhoneNumber)) {
            return;
        }

        $this->phone = $newPhoneNumber;
        $this->phoneConfirmed = $phoneConfirmed;
        $this->updatedAt = new DateTimeImmutable('now');

        if ($newPhoneNumber === null || $phoneConfirmed) {
            return;
        }

        $this->recordEvent(
            new UserPhoneChangedEvent(
                userUuid: $this->uuid->value
            )
        );
    }

    public function confirmPhoneNumber(): void
    {
        if ($this->phoneConfirmed) {
            return;
        }

        $this->phoneConfirmed = true;
        $this->updatedAt = new DateTimeImmutable('now');
    }
}
