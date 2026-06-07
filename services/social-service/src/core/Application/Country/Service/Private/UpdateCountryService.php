<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Private;

use Core\Application\Country\DTO\Private\CountryDto;
use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\Country\Mapper\CountryApplicationMapper;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Country\UseCase\Private\UpdateCountryUseCase;
use Core\Domain\Country\Repository\CountryCommandRepositoryInterface;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;

final readonly class UpdateCountryService implements UpdateCountryUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryCommandRepositoryInterface $countryCommandRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    #[\Override]
    public function execute(CountryDto $countryDto): CountryDto
    {
        $foundCountry = $this->countryQueryRepository->findByIsoCode($countryDto->countryIsoCode);
        if ($foundCountry === null) {
            throw new CountryNotFoundException($countryDto->countryIsoCode);
        }

        $foundCountry->update(
            name: $countryDto->name,
            active: $countryDto->active
        );

        $updated = $this->countryCommandRepository->update($foundCountry);

        return $this->countryApplicationMapper->toPrivateCountryDto($updated);
    }
}
