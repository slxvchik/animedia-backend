<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Query;

use Core\Application\Language\DTO\LanguagePublicResponseDto;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\Language\UseCase\Query\SearchLanguageUserUseCase;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class SearchLanguageUserService implements SearchLanguageUserUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    /**
     * @return Page<LanguagePublicResponseDto>
     */
    #[\Override]
    public function execute(?string $languageIsoCode, ?string $name, Pageable $pageable): Page
    {
        $languagePage = $this->languageQueryRepository->search(
            pageable: $pageable,
            active: true,
            languageIsoCode: $languageIsoCode,
            name: $name
        );

        $languageResponseDtoList = [];
        foreach ($languagePage->content as $language) {
            $languageResponseDtoList[] = $this->languageApplicationMapper->toPublicLanguageResponseDto($language);
        }

        return $languagePage->changeContent($languageResponseDtoList);
    }
}
