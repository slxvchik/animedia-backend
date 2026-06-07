<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Private;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\Country\UseCase\Private\DeleteCountryUseCase;
use Core\Domain\Country\Repository\CountryCommandRepositoryInterface;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;

final readonly class DeleteCountryService implements DeleteCountryUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryCommandRepositoryInterface $countryCommandRepository
    ) {}

    #[\Override]
    public function execute(string $countryIsoCode): void
    {
        $exists = $this->countryQueryRepository->existsByIsoCode($countryIsoCode);
        if (!$exists) {
            throw new CountryNotFoundException($countryIsoCode);
        }

        $this->countryCommandRepository->delete($countryIsoCode);
    }
}
