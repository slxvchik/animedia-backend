<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Private;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;
use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Private\GetPhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class GetPhoneCodeService implements GetPhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper
    ) {}

    #[\Override]
    public function execute(string $countryIsoCode, string $phoneIsoCode): PhoneCodeResponseDto
    {
        $phoneCode = $this->phoneCodeQueryRepository->findByCountryIsoCodeAndPhoneIsoCode(
            countryIsoCode: $countryIsoCode,
            phoneIsoCode: $phoneIsoCode
        );
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($countryIsoCode);
        }

        $country = $this->countryQueryRepository->findByIsoCode($countryIsoCode);
        if ($country === null) {
            throw new CountryNotFoundException($countryIsoCode);
        }

        return $this->phoneCodeApplicationMapper->toPrivatePhoneCodeResponseDto(
            phoneCode: $phoneCode,
            country: $country
        );
    }
}
