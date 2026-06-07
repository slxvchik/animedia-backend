<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Public;

use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Country\UseCase\Public\SearchCountryUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class SearchCountryService implements SearchCountryUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    /**
     * @return Page<CountryResponseDto>
     */
    #[\Override]
    public function execute(?string $countryIsoCode, ?string $name, Pageable $pageable): Page
    {
        $countryPage = $this->countryQueryRepository->search(
            pageable: $pageable,
            active: true,
            countryIsoCode: $countryIsoCode,
            name: $name
        );

        $countryDtoList = [];
        $countryEntityList = $countryPage->content;
        foreach ($countryEntityList as $countryEntity) {
            $countryDtoList[] = $this->countryApplicationMapper->toPublicCountryResponseDto($countryEntity);
        }

        return $countryPage->changeContent($countryDtoList);
    }
}
