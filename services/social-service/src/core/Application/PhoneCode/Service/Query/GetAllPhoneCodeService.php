<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Query;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Query\GetAllPhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

final readonly class GetAllPhoneCodeService implements GetAllPhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    /**
     * @return Page<PhoneCodeResponseDto>
     */
    #[\Override]
    public function execute(Pageable $pageable): Page
    {
        $phoneCodesPage = $this->phoneCodeQueryRepository->findAll(
            pageable: $pageable
        );

        if (empty($phoneCodesPage->content)) {
            return $phoneCodesPage;
        }

        $countryIsoCodes = array_unique(
            array_map(
                static fn (PhoneCode $code) => $code->countryIsoCode,
                $phoneCodesPage->content
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
        foreach ($phoneCodesPage->content as $phoneCode) {
            $countryResponseDtoOrNull = $this->countryApplicationMapper->toCountryResponseDto($countriesByIsoCodeMap[$phoneCode->countryIsoCode]);
            $phoneCodeResponseDtoList[] = $this->phoneCodeApplicationMapper->toPhoneCodeResponseDto(
                phoneCode: $phoneCode,
                countryResponseDto: $countryResponseDtoOrNull
            );
        }

        return $phoneCodesPage->changeContent($phoneCodeResponseDtoList);
    }
}
