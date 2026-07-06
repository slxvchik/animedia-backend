<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetUser;

use Core\Application\User\Query\GetUserRelatedEntities\GetUserRelatedEntitiesService;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Application\User\Query\Shared\Mapper\UserApplicationQueryMapperInterface;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class GetUserService implements GetUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface        $userQueryRepository,
        private UserApplicationQueryMapperInterface $userApplicationMapper,
        private GetUserRelatedEntitiesService $getUserRelationsService
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

        $userRelations = $this->getUserRelationsService->execute(
            languageIsoCodeList: $user->languageIsoCodeList,
            countryIsoCodeList: $user->countryIsoCode ? [$user->countryIsoCode] : null,
            phoneCodeUuidList: $user->phone?->phoneCodeUuid ? [$user->phone?->phoneCodeUuid] : null
        );

        $languageDtoList = $user->languageIsoCodeList
            ? array_filter(
                array_map(static fn(string $code) => $userRelations->languageDtoMap[$code] ?? null, $user->languageIsoCodeList)
            )
            : null;

        return $this->userApplicationMapper->toUserResponseDto(
            user: $user,
            phoneNumberResponseDto: $userRelations->phoneCodeDtoMap[$user->phone?->phoneCodeUuid] ?? null,
            languageResponseDtoList: $languageDtoList,
            countryResponseDto: $userRelations->countryDtoMap[$user->countryIsoCode] ?? null,
        );
    }
}
