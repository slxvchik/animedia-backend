<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Query;

use Core\Application\Country\DTO\CountryDto;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Country\UseCase\Query\SearchCountryAdminUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class SearchCountryAdminService implements SearchCountryAdminUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    /**
     * @return Page<CountryDto>
     */
    #[\Override]
    public function execute(?string $countryIsoCode, ?string $name, ?bool $isActive, Pageable $pageable): Page
    {
        $countryPage = $this->countryQueryRepository->search(
            pageable: $pageable,
            active: $isActive,
            countryIsoCode: $countryIsoCode,
            name: $name
        );

        $countryDtoList = [];
        foreach ($countryPage->content as $countryEntity) {
            $countryDtoList[] = $this->countryApplicationMapper->toPrivateCountryDto($countryEntity);
        }

        return $countryPage->changeContent($countryDtoList);
    }
}
