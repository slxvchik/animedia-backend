<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetAllPhoneCodeUseCase
{
    /**
     * @return Page<PhoneCodeResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
