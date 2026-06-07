<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Query;

use Core\Application\Country\DTO\CountryPublicResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchCountryUserUseCase
{
    /**
     * @return Page<CountryPublicResponseDto>
     */
    public function execute(?string $countryIsoCode, ?string $name, Pageable $pageable): Page;
}
