<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Public;

use Core\Application\PhoneCode\DTO\Public\PhoneCodeResponseDto;

interface GetPhoneCodeListUseCase
{
    /**
     * @return PhoneCodeResponseDto[]
     */
    public function execute(): array;
}
