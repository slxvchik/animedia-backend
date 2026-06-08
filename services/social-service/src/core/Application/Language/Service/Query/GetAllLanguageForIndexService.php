<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Query;

use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\Language\UseCase\Query\GetAllLanguageForIndexUseCase;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class GetAllLanguageForIndexService implements GetAllLanguageForIndexUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    /**
     * @return Page<LanguageResponseDto>
     */
    #[\Override]
    public function execute(Pageable $pageable): Page
    {
        $languagePage = $this->languageQueryRepository->findAll(
            pageable: $pageable
        );

        $languageResponseDtoList = [];
        foreach ($languagePage->content as $language) {
            $languageResponseDtoList[] = $this->languageApplicationMapper->toLanguageResponseDto($language);
        }

        return $languagePage->changeContent($languageResponseDtoList);
    }
}
