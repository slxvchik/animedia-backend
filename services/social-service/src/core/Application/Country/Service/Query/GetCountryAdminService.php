<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Query;

use Core\Application\Country\DTO\CountryDto;
use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Country\UseCase\Query\GetCountryAdminUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;

final readonly class GetCountryAdminService implements GetCountryAdminUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    #[\Override]
    public function execute(string $countryIsoCode): CountryDto
    {
        $country = $this->countryQueryRepository->findByIsoCode($countryIsoCode);
        if ($country === null) {
            throw new CountryNotFoundException($countryIsoCode);
        }

        return $this->countryApplicationMapper->toCountryDto($country);
    }
}
