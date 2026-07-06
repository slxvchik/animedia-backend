<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetUserList;

use Core\Application\User\Query\GetUserRelatedEntities\GetUserRelatedEntitiesService;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Application\User\Query\Shared\Exception\UsersNotFoundException;
use Core\Application\User\Query\Shared\Mapper\UserApplicationQueryMapperInterface;
use Core\Domain\User\Entity\User;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class GetUserListService implements GetUserListUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface        $userQueryRepository,
        private UserApplicationQueryMapperInterface $userApplicationMapper,
        private GetUserRelatedEntitiesService $getUserRelationsService
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
            $foundUserIdList = array_map(static fn(User $user) => $user->uuid, $users);
            $notFoundIdList = array_diff($userUuidList, $foundUserIdList);
            throw new UsersNotFoundException(array_values($notFoundIdList));
        }

        $allLanguageIsoCodeList = array_map(static fn(User $user) => $user->languageIsoCodeList, $users)
            |> (static fn($array) => array_merge(...$array))
            |> array_filter(...)
            |> array_unique(...);

        $allCountryIsoCodeList = array_map(static fn(User $user) => $user->countryIsoCode, $users)
            |> (static fn(array $array) => array_merge(...$array))
            |> array_filter(...)
            |> array_unique(...);

        $allPhoneCodeUuidList = array_map(static fn(User $user) => $user->phone?->phoneCodeUuid, $users)
            |> array_filter(...)
            |> array_unique(...);

        $usersRelations = $this->getUserRelationsService->execute(
            languageIsoCodeList: $allLanguageIsoCodeList,
            countryIsoCodeList: $allCountryIsoCodeList,
            phoneCodeUuidList: $allPhoneCodeUuidList
        );

        $userDtoList = [];
        foreach ($users as $user) {
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

        return $userDtoList;
    }
}
