<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetAllUser;

use Core\Application\User\Query\GetUserRelatedEntities\GetUserRelatedEntitiesService;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Application\User\Query\Shared\Mapper\UserApplicationQueryMapperInterface;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;
use Core\Domain\User\Entity\User;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class GetAllUserService implements GetAllUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface        $userQueryRepository,
        private UserApplicationQueryMapperInterface $userApplicationMapper,
        private GetUserRelatedEntitiesService $getUsersRelationsService
    ) {}

    /**
     * @return Page<UserResponseDto>
     */
    #[\Override]
    public function execute(Pageable $pageable): Page
    {
        $usersPage = $this->userQueryRepository->findAll($pageable);
        if (count($usersPage->content) === 0) {
            return Page::empty();
        }

        $allLanguageIsoCodeList = array_map(static fn(User $user) => $user->languageIsoCodeList, $usersPage->content)
            |> (static fn(array $array) => array_merge(...$array))
            |> array_filter(...)
            |> array_unique(...);

        $allCountryIsoCodeList = array_map(static fn(User $user) => $user->countryIsoCode, $usersPage->content)
            |> (static fn(array $array) => array_merge(...$array))
            |> array_filter(...)
            |> array_unique(...);

        $allPhoneCodeUuidList = array_map(static fn(User $user) => $user->phone?->phoneCodeUuid, $usersPage->content)
            |> array_filter(...)
            |> array_unique(...);

        $usersRelations = $this->getUsersRelationsService->execute(
            languageIsoCodeList: $allLanguageIsoCodeList,
            countryIsoCodeList: $allCountryIsoCodeList,
            phoneCodeUuidList: $allPhoneCodeUuidList
        );

        $userDtoList = [];
        foreach ($usersPage->content as $user) {
            $languageDtoList = $user->languageIsoCodeList
                ? array_filter(
                    array_map(static fn(string $code) => $usersRelations->languageDtoMap[$code] ?? null, $user->languageIsoCodeList)
                )
                : null;
            $userDtoList[] = $this->userApplicationMapper->toUserResponseDto(
                user: $user,
                phoneNumberResponseDto: $usersRelations->phoneCodeDtoMap[$user->phone?->phoneCodeUuid] ?? null,
                languageResponseDtoList: $languageDtoList,
                countryResponseDto: $usersRelations->countryDtoMap[$user->countryIsoCode] ?? null,
            );
        }

        return $usersPage->changeContent($userDtoList);
    }
}
