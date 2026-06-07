<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Private;

use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchPhoneCodeUseCase
{
    /**
     * @return Page<PhoneCodeResponseDto>
     */
    public function execute(?string $phoneCode, ?bool $isActive, Pageable $pageable): Page;
}
