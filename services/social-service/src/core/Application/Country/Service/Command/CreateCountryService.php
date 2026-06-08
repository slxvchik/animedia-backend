<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Command;

use Core\Application\Country\DTO\CountryDto;
use Core\Application\Country\Exception\CountryIsoCodeExistsException;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Country\UseCase\Command\CreateCountryUseCase;
use Core\Domain\Country\Repository\CountryCommandRepositoryInterface;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;

final readonly class CreateCountryService implements CreateCountryUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryCommandRepositoryInterface $countryCommandRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    #[\Override]
    public function execute(CountryDto $countryDto): CountryDto
    {
        if ($this->countryQueryRepository->existsByIsoCode($countryDto->countryIsoCode)) {
            throw new CountryIsoCodeExistsException($countryDto->countryIsoCode);
        }

        $country = $this->countryApplicationMapper->toCountry($countryDto);
        $created = $this->countryCommandRepository->create($country);

        return $this->countryApplicationMapper->toCountryDto($created);
    }
}
