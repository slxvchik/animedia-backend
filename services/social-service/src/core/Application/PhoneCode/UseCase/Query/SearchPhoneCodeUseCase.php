<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodePrivateResponseDto;
use Core\Domain\Shared\Pagination\Entity\Page;
use Core\Domain\Shared\Pagination\Entity\Pageable;

interface SearchPhoneCodeUseCase
{
    /**
     * @return Page<PhoneCodePrivateResponseDto>
     */
    public function execute(?string $phoneCode, ?bool $isActive, Pageable $pageable): Page;
}
