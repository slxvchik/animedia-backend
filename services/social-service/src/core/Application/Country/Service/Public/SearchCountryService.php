<?php

namespace Core\Application\Country\Service\Public;

use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Application\Country\Mapper\CountryApplicationMapper;
use Core\Application\Country\UseCase\Public\SearchCountryUseCase;
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
     * @return Page<CountryResponseDto>
     */
    public function execute(?string $countryIsoCode, ?string $name, Pageable $pageable): Page
    {
        $pageCountryEntityList = $this->countryQueryRepository->search(
            $countryIsoCode,
            $name,
            null,
            $pageable
        );

        $countryDtoList = [];
        $countryEntityList = $pageCountryEntityList->content;
        foreach ($countryEntityList as $countryEntity) {
            $countryDtoList[] = $this->countryApplicationMapper->toCountryResponseDto($countryEntity);
        }

        return $pageCountryEntityList->changeContent($countryDtoList);
    }
}
