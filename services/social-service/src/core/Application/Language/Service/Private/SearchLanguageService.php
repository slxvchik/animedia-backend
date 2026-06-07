<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Private;

use Core\Application\Language\DTO\Public\LanguageResponseDto;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\Language\UseCase\Private\SearchLanguageUseCase;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class SearchLanguageService implements SearchLanguageUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    /**
     * @return Page<LanguageResponseDto>
     */
    #[\Override]
    public function execute(?string $languageIsoCode, ?string $name, ?bool $isActive, Pageable $pageable): Page
    {
        $languagePage = $this->languageQueryRepository->search(
            pageable: $pageable,
            active: $isActive,
            languageIsoCode: $languageIsoCode,
            name: $name
        );

        $languageDtoList = [];
        foreach ($languagePage->content as $languageEntity) {
            $languageDtoList[] = $this->languageApplicationMapper->toPrivateLanguageDto($languageEntity);
        }

        return $languagePage->changeContent($languageDtoList);
    }
}
