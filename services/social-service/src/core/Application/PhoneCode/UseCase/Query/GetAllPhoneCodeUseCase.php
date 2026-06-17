<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Domain\Shared\Pagination\Page;
use Core\Domain\Shared\Pagination\Pageable;

interface GetAllPhoneCodeUseCase
{
    /**
     * @return Page<PhoneCodeResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
