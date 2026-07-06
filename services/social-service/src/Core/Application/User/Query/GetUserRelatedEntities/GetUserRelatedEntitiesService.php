<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetUserRelatedEntities;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class GetUserRelatedEntitiesService
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper,
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper
    ) {}

    public function execute(?array $languageIsoCodeList, ?array $countryIsoCodeList, ?array $phoneCodeUuidList): UserRelatedEntities
    {
        $languageDtoMap = [];
        if (!empty($languageIsoCodeList)) {
            $foundLanguageList = $this->languageQueryRepository->findByIsoCodeList(
                isoCodeList: $languageIsoCodeList
            );
            foreach ($foundLanguageList as $language) {
                $languageDtoMap[$language->isoCode] = $this->languageApplicationMapper->toLanguageResponseDto(
                    language: $language
                );
            }
        }

        $countryDtoMap = [];
        if (!empty($countryIsoCodeList)) {
            $countryList = $this->countryQueryRepository->findByIsoCodeList($countryIsoCodeList);
            foreach ($countryList as $country) {
                $countryDtoMap[$country->isoCode] = $this->countryApplicationMapper->toCountryResponseDto(
                    country: $country
                );
            }
        }

        $phoneCodeDtoMap = [];
        if (!empty($phoneCodeUuidList)) {
            $phoneCodeList = $this->phoneCodeQueryRepository->findByPhoneCodeUuidList($phoneCodeUuidList);
            foreach ($phoneCodeList as $phoneCode) {
                $phoneCodeDtoMap[$phoneCode->uuid->value] = $this->phoneCodeApplicationMapper->toPhoneCodeResponseDto(
                    phoneCode: $phoneCode
                );
            }
        }

        return new UserRelatedEntities(
            languageDtoMap: $languageDtoMap,
            countryDtoMap: $countryDtoMap,
            phoneCodeDtoMap: $phoneCodeDtoMap
        );
    }
}
