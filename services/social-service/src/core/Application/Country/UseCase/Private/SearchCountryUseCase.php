<?php

namespace Core\Application\Country\UseCase\Private;

use Core\Application\Country\DTO\Private\CountryDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchCountryUseCase
{
    /**
     * @return Page<CountryDto>
     */
    public function execute(?string $countryIsoCode, ?string $name, ?bool $isActive, Pageable $pageable): Page;
}
