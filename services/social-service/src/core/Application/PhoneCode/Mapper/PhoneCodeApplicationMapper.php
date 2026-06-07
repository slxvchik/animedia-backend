<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Mapper;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeRequestDto;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto as PrivatePhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\Public\PhoneCodeResponseDto as PublicPhoneCodeResponseDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\PhoneCode\Entity\PhoneCode;

final readonly class PhoneCodeApplicationMapper implements PhoneCodeApplicationMapperInterface
{
    public function __construct(
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    #[\Override]
    public function toPhoneCode(PhoneCodeRequestDto $phoneCodeRequestDto): PhoneCode
    {
        return new PhoneCode(
            countryIsoCode: $phoneCodeRequestDto->countryIsoCode,
            phoneCode: $phoneCodeRequestDto->phoneCode,
            active: $phoneCodeRequestDto->isActive
        );
    }

    #[\Override]
    public function toPrivatePhoneCodeResponseDto(PhoneCode $phoneCode, ?Country $country): PrivatePhoneCodeResponseDto
    {
        $countryDtoOrNull = $country !== null ? $this->countryApplicationMapper->toPrivateCountryDto($country) : null;
        return new PrivatePhoneCodeResponseDto(
            phoneCode: $phoneCode->code,
            isActive: $phoneCode->active,
            country: $countryDtoOrNull,
        );
    }

    #[\Override]
    public function toPublicPhoneCodeResponseDto(PhoneCode $phoneCode, Country $country): PublicPhoneCodeResponseDto
    {
        return new PublicPhoneCodeResponseDto(
            phoneCode: $phoneCode->code,
            country: $this->countryApplicationMapper->toPublicCountryResponseDto($country),
        );
    }
}
