<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Private;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeRequestDto;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;
use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Private\UpdatePhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeCommandRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class UpdatePhoneCodeService implements UpdatePhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeCommandRepositoryInterface $phoneCodeCommandRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper
    ) {}

    #[\Override]
    public function execute(PhoneCodeRequestDto $phoneCodeRequestDto): PhoneCodeResponseDto
    {
        $phoneCode = $this->phoneCodeQueryRepository->findByCountryIsoCodeAndPhoneIsoCode(
            countryIsoCode: $phoneCodeRequestDto->countryIsoCode,
            phoneIsoCode: $phoneCodeRequestDto->phoneCode
        );
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($phoneCodeRequestDto->countryIsoCode);
        }

        $country = $this->countryQueryRepository->findByIsoCode($phoneCodeRequestDto->countryIsoCode);
        if ($country === null) {
            throw new CountryNotFoundException($phoneCodeRequestDto->countryIsoCode);
        }

        $phoneCode->update(
            active: $phoneCodeRequestDto->isActive
        );

        $updated = $this->phoneCodeCommandRepository->update($phoneCode);

        return $this->phoneCodeApplicationMapper->toPrivatePhoneCodeResponseDto(
            phoneCode: $updated,
            country: $country
        );
    }
}
