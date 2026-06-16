<?php

namespace Core\Application\User\Query\GetUser;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Application\User\Query\Shared\Mapper\UserApplicationMapperInterface;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class GetUserService implements GetUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private UserApplicationMapperInterface $userApplicationMapper,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper,
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    #[\Override]
    public function execute(string $userUuid): UserResponseDto
    {
        $user = $this->userQueryRepository->findByUserUuid(
            userUuid: $userUuid
        );
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        $languagesDto = [];
        if ($user->languageIsoCodeList !== null) {
            $languages = $this->languageQueryRepository->findByIsoCodeList(
                isoCodeList: $user->languageIsoCodeList
            );
            foreach ($languages as $language) {
                $languagesDto[] = $this->languageApplicationMapper->toLanguageResponseDto(
                    language: $language
                );
            }
        }

        $countryDtoOrNull = null;
        if ($user->countryIsoCode !== null) {
            $countryOrNull = $this->countryQueryRepository->findByIsoCode(
                isoCode: $user->countryIsoCode
            );
            $countryDtoOrNull = $this->countryApplicationMapper->toCountryResponseDto(
                country: $countryOrNull
            );
        }

        return $this->userApplicationMapper->toUserResponseDto(
            user: $user,
            languageResponseDtoList: $languagesDto,
            countryResponseDto: $countryDtoOrNull,
        );
    }
}
