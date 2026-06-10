<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Mapper;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\PhoneCode\DTO\CreatePhoneCodeCommandDto;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\UpdatePhoneCodeCommandDto;
use Core\Domain\Country\Entity\Country;
use Core\Domain\PhoneCode\Entity\PhoneCode;

final readonly class PhoneCodeApplicationMapper implements PhoneCodeApplicationMapperInterface
{
    public function __construct(
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    #[\Override]
    public function fromCreatePhoneCodeCommandDto(CreatePhoneCodeCommandDto $phoneCodeCommandDto, string $generatedUuid): PhoneCode
    {
        return new PhoneCode(
            uuid: $generatedUuid,
            countryIsoCode: $phoneCodeCommandDto->countryIsoCode,
            phoneCode: $phoneCodeCommandDto->phoneCode,
            active: $phoneCodeCommandDto->isActive
        );
    }

    #[\Override]
    public function fromUpdatePhoneCodeCommandDto(UpdatePhoneCodeCommandDto $phoneCodeCommandDto): PhoneCode
    {
        return new PhoneCode(
            uuid: $phoneCodeCommandDto->uuid,
            countryIsoCode: $phoneCodeCommandDto->countryIsoCode,
            phoneCode: $phoneCodeCommandDto->phoneCode,
            active: $phoneCodeCommandDto->isActive
        );
    }

    #[\Override]
    public function toPhoneCodeResponseDto(PhoneCode $phoneCode, Country $country): PhoneCodeResponseDto
    {
        return new PhoneCodeResponseDto(
            uuid: $phoneCode->uuid,
            phoneCode: $phoneCode->code,
            isActive: $phoneCode->active,
            country: $this->countryApplicationMapper->toCountryResponseDto($country)
        );
    }
}
