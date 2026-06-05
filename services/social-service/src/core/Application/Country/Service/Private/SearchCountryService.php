<?php

namespace Core\Application\Country\Service\Private;

use Core\Application\Country\DTO\Private\CountryDto;
use Core\Application\Country\Mapper\CountryApplicationMapper;
use Core\Application\Country\UseCase\Private\SearchCountryUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class SearchCountryService implements SearchCountryUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapper $countryApplicationMapper
    ) {}

    /**
     * @return Page<CountryDto>
     */
    public function execute(?string $countryIsoCode, ?string $name, ?bool $isActive, Pageable $pageable): Page
    {
        $pageCountryEntityList = $this->countryQueryRepository->search(
            $countryIsoCode,
            $name,
            $isActive,
            $pageable
        );

        $countryDtoList = [];
        $countryEntityList = $pageCountryEntityList->content;
        foreach ($countryEntityList as $countryEntity) {
            $countryDtoList[] = $this->countryApplicationMapper->toCountryDto($countryEntity);
        }

        return $pageCountryEntityList->changeContent($countryDtoList);
    }
}
