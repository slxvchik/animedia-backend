<?php

namespace Core\Domain\UserProfile\Entity;

use Core\Domain\Country\Entity\Country;
use Core\Domain\PhoneNumber\Entity\PhoneNumber;
use Core\Domain\UserProfile\Exception\InvalidUserProfileIdException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameCodeException;
use Core\Domain\UserProfile\Exception\InvalidUserProfileNicknameException;

class UserProfile
{
    public function __construct(
        private readonly string $userUuid,
        private string          $username,
        private string          $usernameCode,
        private ?Country        $country,
        private ?PhoneNumber    $phone,
        private ?string         $imageUuid,
        private ?string         $color,
        private ?string         $description
    ) {
        $this->assertUserUuid(trim($userUuid));
        $this->assertUsername(trim($username));
        $this->assertUsernameCode(trim($usernameCode));
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

    public function getCountry(): ?Country
    {
        return $this->country;
    }

    public function setCountry(?Country $country): void
    {
        $this->country = $country;
    }

    public function getUserUuid(): string
    {
        return $this->userUuid;
    }

    public function getUsername(): string
    {
        return $this->username;
    }

    public function setUsername(string $username): void
    {
        $cleanedUsername = trim($username);
        $this->assertUsername($cleanedUsername);
        $this->username = $username;
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

    public function getPhone(): ?PhoneNumber
    {
        return $this->phone;
    }

    public function setPhone(?PhoneNumber $phone): void
    {
        $this->phone = $phone;
    }

    public function getImageUuid(): ?string
    {
        return $this->imageUuid;
    }

    public function setImageUuid(?string $imageUuid): void
    {
        $this->imageUuid = $imageUuid;
    }

    public function getColor(): ?string
    {
        return $this->color;
    }

    public function setColor(?string $color): void
    {
        $this->color = $color;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): void
    {
        $this->description = $description;
    }
}
