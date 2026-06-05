<?php

namespace Core\Application\Country\UseCase;

use Core\Application\Country\DTO\CountryDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchCountryUseCase
{
    /**
     * @param string|null $name
     * @param bool|null $isActive
     * @param Pageable $pageable
     * @return Page<CountryDto>
     */
    public function execute(?string $name, ?bool $isActive, Pageable $pageable): Page;
}
