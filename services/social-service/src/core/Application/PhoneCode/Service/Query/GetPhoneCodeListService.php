<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Query;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Query\GetPhoneCodeListUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class GetPhoneCodeListService implements GetPhoneCodeListUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    /**
     * @param string[] $phoneCodeUuidList
     * @return PhoneCodeResponseDto[]
     */
    #[\Override]
    public function execute(array $phoneCodeUuidList): array
    {
        $phoneCodes = $this->phoneCodeQueryRepository->findByPhoneCodeUuidList($phoneCodeUuidList);

        if (empty($phoneCodes)) {
            return $phoneCodes;
        }

        $countryIsoCodes = array_unique(
            array_map(
                static fn (PhoneCode $code) => $code->countryIsoCode,
                $phoneCodes
            )
        );

        $countries = $this->countryQueryRepository->findByIsoCodeList(
            isoCodeList: $countryIsoCodes
        );

        $countriesByIsoCodeMap = [];
        foreach ($countries as $country) {
            $countriesByIsoCodeMap[$country->isoCode] = $country;
        }

        $phoneCodeResponseDtoList = [];
        foreach ($phoneCodes as $phoneCode) {
            $countryResponseDtoOrNull = $this->countryApplicationMapper->toCountryResponseDto($countriesByIsoCodeMap[$phoneCode->countryIsoCode]);
            $phoneCodeResponseDtoList[] = $this->phoneCodeApplicationMapper->toPhoneCodeResponseDto(
                phoneCode: $phoneCode,
                countryResponseDto: $countryResponseDtoOrNull
            );
        }

        return $phoneCodeResponseDtoList;
    }
}
