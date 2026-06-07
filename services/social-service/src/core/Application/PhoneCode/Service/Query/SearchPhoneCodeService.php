<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Query;

use Core\Application\PhoneCode\DTO\PhoneCodePrivateResponseDto;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Query\SearchPhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Entity\PhoneCode;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class SearchPhoneCodeService implements SearchPhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper
    ) {}

    /**
     * @return Page<PhoneCodePrivateResponseDto>
     */
    #[\Override]
    public function execute(?string $phoneCode, ?bool $isActive, Pageable $pageable): Page
    {
        $phoneCodesPage = $this->phoneCodeQueryRepository->search(
            pageable: $pageable,
            active: $isActive,
            phoneCode: $phoneCode
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
            $countryOrNull = $countriesByIsoCodeMap[$phoneCode->countryIsoCode] ?? null;
            $phoneCodeResponseDtoList[] = $this->phoneCodeApplicationMapper->toPrivatePhoneCodeResponseDto(
                phoneCode: $phoneCode,
                country: $countryOrNull
            );
        }

        return $phoneCodesPage->changeContent($phoneCodeResponseDtoList);
    }
}
