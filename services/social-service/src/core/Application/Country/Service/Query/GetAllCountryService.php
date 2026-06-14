<?php

declare(strict_types=1);

namespace Core\Application\Country\Service\Query;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\Country\UseCase\Query\GetAllCountryUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

final readonly class GetAllCountryService implements GetAllCountryUseCase
{
    public function __construct(
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private CountryApplicationMapperInterface $countryApplicationMapper
    ) {}

    /**
     * @return Page<CountryResponseDto>
     */
    #[\Override]
    public function execute(Pageable $pageable): Page
    {
        $countryPage = $this->countryQueryRepository->findAll(
            pageable: $pageable
        );

        $countryDtoList = [];
        foreach ($countryPage->content as $countryEntity) {
            $countryDtoList[] = $this->countryApplicationMapper->toCountryResponseDto($countryEntity);
        }

        return $countryPage->changeContent($countryDtoList);
    }
}
