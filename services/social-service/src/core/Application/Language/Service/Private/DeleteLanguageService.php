<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Private;

use Core\Application\Language\Exception\LanguageNotFoundException;
use Core\Application\Language\UseCase\Private\DeleteLanguageUseCase;
use Core\Domain\Language\Repository\LanguageCommandRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;

final readonly class DeleteLanguageService implements DeleteLanguageUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageCommandRepositoryInterface $languageCommandRepository
    ) {}

    #[\Override]
    public function execute(string $languageIsoCode): void
    {
        $exists = $this->languageQueryRepository->existsByIsoCode($languageIsoCode);
        if (!$exists) {
            throw new LanguageNotFoundException($languageIsoCode);
        }

        $this->languageCommandRepository->delete($languageIsoCode);
    }
}
