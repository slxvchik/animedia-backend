<?php

namespace Core\Application\PhoneCode\UseCase;

use Core\Application\PhoneCode\DTO\PhoneCodeDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetPhoneCodeListUseCase
{
    /**
     * @return Page<PhoneCodeDto>
     */
    public function execute(Pageable $pageable): Page;
}
