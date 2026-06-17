<?php

declare(strict_types=1);

namespace Core\Application\User\Query\GetAllUser;

use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\User\Query\Shared\DTO\UserResponseDto;
use Core\Application\User\Query\Shared\Mapper\UserApplicationMapperInterface;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class GetAllUserService implements GetAllUserUseCase
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
     * @return Page<UserResponseDto>
     */
    #[\Override]
    public function execute(Pageable $pageable): Page
    {
        $users = $this->userQueryRepository->findAll($pageable);
        if (count($users->content) === 0) {
            return Page::empty();
        }


    }
}
