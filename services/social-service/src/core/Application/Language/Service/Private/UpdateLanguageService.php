<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Private;

use Core\Application\Language\DTO\Private\LanguageDto;
use Core\Application\Language\Exception\LanguageNotFoundException;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\Language\UseCase\Private\UpdateLanguageUseCase;
use Core\Domain\Language\Repository\LanguageCommandRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;

final readonly class UpdateLanguageService implements UpdateLanguageUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageCommandRepositoryInterface $languageCommandRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    #[\Override]
    public function execute(LanguageDto $languageDto): LanguageDto
    {
        $language = $this->languageQueryRepository->findByCode($languageDto->languageIsoCode);
        if ($language === null) {
            throw new LanguageNotFoundException($languageDto->languageIsoCode);
        }

        $language->update(
            name: $languageDto->name,
            active: $languageDto->isActive
        );

        $updated = $this->languageCommandRepository->update($language);

        return $this->languageApplicationMapper->toPrivateLanguageDto($updated);
    }
}
