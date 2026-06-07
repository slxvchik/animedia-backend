<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Query;

use Core\Application\PhoneCode\DTO\PhoneCodePublicResponseDto;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Query\GetAllPhoneCodeListUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class GetAllPhoneCodeListService implements GetAllPhoneCodeListUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper
    ) {}

    /**
     * @return PhoneCodePublicResponseDto[]
     */
    #[\Override]
    public function execute(): array
    {
        $phoneCodes = $this->phoneCodeQueryRepository->findAll();

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
            isoCodeList: $countryIsoCodes,
            active: true
        );

        $countriesByIsoCodeMap = [];
        foreach ($countries as $country) {
            $countriesByIsoCodeMap[$country->isoCode] = $country;
        }

        $phoneCodeResponseDtoList = [];
        foreach ($phoneCodes as $phoneCode) {
            $country = $countriesByIsoCodeMap[$phoneCode->countryIsoCode] ?? null;
            if ($country !== null) {
                $phoneCodeResponseDtoList[] = $this->phoneCodeApplicationMapper->toPublicPhoneCodeResponseDto(
                    phoneCode: $phoneCode,
                    country: $country
                );
            }
        }

        return $phoneCodeResponseDtoList;
    }
}
