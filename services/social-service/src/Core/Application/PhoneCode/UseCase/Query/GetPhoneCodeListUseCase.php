<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\UseCase\Query;

use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;

interface GetPhoneCodeListUseCase
{
    /**
     * @param string[] $phoneCodeUuidList
     * @return PhoneCodeResponseDto[]
     */
    public function execute(array $phoneCodeUuidList): array;
}
