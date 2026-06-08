<?php

declare(strict_types=1);

namespace Core\Application\Country\UseCase\Query;

use Core\Application\Country\DTO\CountryResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetAllForIndexUseCase
{
    /**
     * @return Page<CountryResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
