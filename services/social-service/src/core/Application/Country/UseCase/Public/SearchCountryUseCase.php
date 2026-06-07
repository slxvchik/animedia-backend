<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Public;

use Core\Application\Country\DTO\Public\CountryResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchCountryUseCase
{
    /**
     * @return Page<CountryResponseDto>
     */
    public function execute(?string $countryIsoCode, ?string $name, Pageable $pageable): Page;
}
