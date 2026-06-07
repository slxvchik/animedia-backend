<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Query;

use Core\Application\Country\DTO\CountryDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchCountryAdminUseCase
{
    /**
     * @return Page<CountryDto>
     */
    public function execute(?string $countryIsoCode, ?string $name, ?bool $isActive, Pageable $pageable): Page;
}
