<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Command;

use Core\Application\Language\DTO\LanguageCommandDto;
use Core\Application\Language\DTO\LanguageResponseDto;
use Core\Application\Language\Exception\LanguageNotFoundException;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\Language\UseCase\Command\UpdateLanguageUseCase;
use Core\Domain\Language\Repository\LanguageCommandRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;

final readonly class UpdateLanguageService implements UpdateLanguageUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageCommandRepositoryInterface $languageCommandRepository
    ) {}

    #[\Override]
    public function execute(LanguageCommandDto $languageDto): void
    {
        $language = $this->languageQueryRepository->findByIsoCode($languageDto->languageIsoCode);
        if ($language === null) {
            throw new LanguageNotFoundException($languageDto->languageIsoCode);
        }

        $language->update(
            name: $languageDto->name,
            active: $languageDto->isActive
        );

        $this->languageCommandRepository->update($language);
    }
}
