<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Mapper;

use Core\Application\PhoneCode\DTO\CreatePhoneCodeCommandDto;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\UpdatePhoneCodeCommandDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\PhoneCode\Entity\PhoneCode;

interface PhoneCodeApplicationMapperInterface
{
    public function fromCreatePhoneCodeCommandDto(CreatePhoneCodeCommandDto $phoneCodeCommandDto): PhoneCode;

    public function fromUpdatePhoneCodeCommandDto(UpdatePhoneCodeCommandDto $phoneCodeCommandDto): PhoneCode;

    public function toPhoneCodeResponseDto(PhoneCode $phoneCode, Country $country): PhoneCodeResponseDto;
}
