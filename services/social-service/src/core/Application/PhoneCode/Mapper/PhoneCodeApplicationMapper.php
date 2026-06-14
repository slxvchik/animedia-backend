<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Mapper;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\PhoneCode\DTO\CreatePhoneCodeCommandDto;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\UpdatePhoneCodeCommandDto;
use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;

final readonly class PhoneCodeApplicationMapper implements PhoneCodeApplicationMapperInterface
{
    #[\Override]
    public function fromCreatePhoneCodeCommandDto(CreatePhoneCodeCommandDto $phoneCodeCommandDto, IdentityGeneratorInterface $identityGenerator): PhoneCode
    {
        return PhoneCode::createNew(
            countryIsoCode: $phoneCodeCommandDto->countryIsoCode,
            phoneCode: $phoneCodeCommandDto->phoneCode,
            active: $phoneCodeCommandDto->isActive,
            identityGenerator: $identityGenerator
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
    public function toPhoneCodeResponseDto(?PhoneCode $phoneCode, ?CountryResponseDto $countryResponseDto = null): ?PhoneCodeResponseDto
    {
        if ($phoneCode === null) { return null; }
        return new PhoneCodeResponseDto(
            uuid: $phoneCode->uuid,
            phoneCode: $phoneCode->code,
            isActive: $phoneCode->active,
            country: $countryResponseDto
        );
    }
}
