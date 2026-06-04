<?php

namespace Core\Domain\PhoneNumber\Entity;

use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\PhoneNumber\Exception\InvalidPhoneNumberException;
use Core\Domain\PhoneNumber\Validator\PhoneValidatorInterface;

class PhoneNumber
{
    public function __construct(
        private readonly PhoneValidatorInterface $validator,
        private PhoneCode $code,
        private string $number,
        private bool $confirmed = false
    ) {
        $this->assertNumberIsValid($this->code, $this->number);
    }

    private function assertNumberIsValid(PhoneCode $code, string $number): void
    {
        $cleanNumber = $this->getCleanNumber($number);
        $phoneValid = !$this->validator->validate($code->getPhoneCode(), $cleanNumber, $code->getCountry()->getCountryIsoCode());
        if(!$phoneValid)
            throw new InvalidPhoneNumberException($cleanNumber);
    }

    private function getCleanNumber(string $number): string
    {
        return preg_replace('/[^0-9]/', '', $number);
    }

    public function getCode(): PhoneCode
    {
        return $this->code;
    }

    public function setCode(PhoneCode $code): void
    {
        $this->code = $code;
    }

    public function getNumber(): string
    {
        return $this->number;
    }

    public function setNumber(string $number): void
    {
        $cleanNumber = $this->getCleanNumber($number);
        $this->assertNumberIsValid($this->code, $cleanNumber);
        $this->number = $number;
    }

    public function isConfirmed(): bool
    {
        return $this->confirmed;
    }

    public function setConfirmed(bool $confirmed): void
    {
        $this->confirmed = $confirmed;
    }
}
