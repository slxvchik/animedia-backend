<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodePublicResponseDto;

interface GetAllPhoneCodeListUseCase
{
    /**
     * @return PhoneCodePublicResponseDto[]
     */
    public function execute(): array;
}
