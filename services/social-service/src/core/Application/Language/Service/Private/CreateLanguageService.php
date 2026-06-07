<?php

declare(strict_types=1);

namespace Core\Application\Language\Service\Private;

use Core\Application\Language\DTO\Private\LanguageDto;
use Core\Application\Language\Exception\LanguageExistsException;
use Core\Application\Language\Mapper\LanguageApplicationMapperInterface;
use Core\Application\Language\UseCase\Private\CreateLanguageUseCase;
use Core\Domain\Language\Repository\LanguageCommandRepositoryInterface;
use Core\Domain\Language\Repository\LanguageQueryRepositoryInterface;

final readonly class CreateLanguageService implements CreateLanguageUseCase
{
    public function __construct(
        private LanguageQueryRepositoryInterface $languageQueryRepository,
        private LanguageCommandRepositoryInterface $languageCommandRepository,
        private LanguageApplicationMapperInterface $languageApplicationMapper
    ) {}

    #[\Override]
    public function execute(LanguageDto $languageDto): LanguageDto
    {
        $exists = $this->languageQueryRepository->existsByIsoCode($languageDto->languageIsoCode);
        if ($exists) {
            throw new LanguageExistsException($languageDto->languageIsoCode);
        }

        $language = $this->languageApplicationMapper->toLanguage($languageDto);
        $created = $this->languageCommandRepository->create($language);

        return $this->languageApplicationMapper->toPrivateLanguageDto($created);
    }
}
