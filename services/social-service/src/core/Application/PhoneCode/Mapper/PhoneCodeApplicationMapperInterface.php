<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Mapper;

use Core\Application\PhoneCode\DTO\PhoneCodePrivateResponseDto as PrivatePhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\CommandPhoneCodeRequestDto;
use Core\Application\PhoneCode\DTO\PhoneCodePublicResponseDto as PublicPhoneCodeResponseDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\PhoneCode\Entity\PhoneCode;

interface PhoneCodeApplicationMapperInterface
{
    public function toPhoneCode(CommandPhoneCodeRequestDto $phoneCodeRequestDto): PhoneCode;
    public function toPrivatePhoneCodeResponseDto(PhoneCode $phoneCode, ?Country $country): PrivatePhoneCodeResponseDto;
    public function toPublicPhoneCodeResponseDto(PhoneCode $phoneCode, Country $country): PublicPhoneCodeResponseDto;
}
