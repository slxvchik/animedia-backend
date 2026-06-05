<?php

namespace Core\Application\PhoneCode\UseCase\Public;

use Core\Application\PhoneCode\DTO\Public\PhoneCodeResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface GetPhoneCodeListUseCase
{
    /**
     * @return Page<PhoneCodeResponseDto>
     */
    public function execute(Pageable $pageable): Page;
}
