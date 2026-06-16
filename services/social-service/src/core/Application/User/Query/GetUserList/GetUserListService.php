<?php

namespace Core\Application\User\Query\GetUserList;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Application\User\Query\Shared\Exception\UsersNotFoundException;
use Core\Application\User\Query\Shared\Mapper\UserApplicationMapperInterface;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class GetUserListService implements GetUserListUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private UserApplicationMapperInterface $userApplicationMapper,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper,
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    /**
     * @param string[] $userUuidList
     * @return UserResponseDto[]
     */
    #[\Override]
    public function execute(array $userUuidList): array
    {
        $users = $this->userQueryRepository->findByUserUuidList(
            userUuidList: $userUuidList
        );
        if (count($userUuidList) !== count($users)) {
            $foundUserIdList = array_map(static fn ($user) => $user->uuid, $users);
            $notFoundIdList = array_diff($userUuidList, $foundUserIdList);
            throw new UsersNotFoundException(array_values($notFoundIdList));
        }

        $allCountryIsoCodeList = array_unique(array_filter(
            array_map(static fn ($user) => $user->countryIsoCode, $users)
        ));
        $countryDtoMap = [];
        if (!empty($allCountryIsoCodeList)) {
            $foundCountryList = $this->countryQueryRepository->findByIsoCodeList(
                isoCodeList: $allCountryIsoCodeList
            );
            foreach ($foundCountryList as $country) {
                $countryDtoMap[$country->isoCode] = $this->countryApplicationMapper->toCountryResponseDto(
                    country: $country
                );
            }
        }

//        $allLanguageIsoCodeList = array_map(static fn($user) => $user->languageIsoCodeList, $users)
//            |> (fn($x) => array_filter($x, static fn($languageIsoCode) => $languageIsoCode !== null))
//            |> array_unique(...);
        $allLanguageIsoCodeList = array_unique(array_merge(
            ...array_filter(array_map(static fn($user) => $user->languageIsoCodeList, $users))
        ));
        $languageDtoMap = [];
        if (!empty($allLanguageIsoCodeList)) {
            $foundLanguageList = $this->languageQueryRepository->findByIsoCodeList(
                isoCodeList: $allLanguageIsoCodeList
            );
            foreach ($foundLanguageList as $language) {
                $languageDtoMap[$language->isoCode] = $this->languageApplicationMapper->toLanguageResponseDto(
                    language: $language
                );
            }
        }

        $userDtoList = [];
        foreach ($users as $user) {

            $languageDtoList = [];
            if ($user->languageIsoCodeList !== null) {
                foreach ($user->languageIsoCodeList as $languageIsoCode) {
                    if (!empty($languageDtoMap[$languageIsoCode])) {
                        $languageDtoList[] = $languageDtoMap[$languageIsoCode->isoCode];
                    }
                }
            }

            $userDtoList[] = $this->userApplicationMapper->toUserResponseDto(
                user: $user,
                languageResponseDtoList: $languageDtoList ?: null,
                countryResponseDto: $countryDtoMap[$user->countryIsoCode] ?? null
            );
        }

        return $userDtoList;
    }
}
