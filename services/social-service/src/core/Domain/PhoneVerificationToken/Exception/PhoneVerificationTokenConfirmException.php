<?php

declare(strict_types=1);

namespace Core\Domain\PhoneVerificationToken\Exception;

use Core\Domain\Shared\Exception\AppException;
use Core\Domain\Shared\Exception\AppExceptionStatus;
use Core\Domain\Shared\ValueObject\PhoneNumber;

class PhoneVerificationTokenConfirmException extends AppException
{
    public function __construct(PhoneNumber $curNumber, PhoneNumber $requestedNumber)
    {
        $curPhone = $curNumber->code . ' ' . $curNumber->number;
        $requestedPhone = $requestedNumber->code . ' ' . $requestedNumber->number;
        parent::__construct(AppExceptionStatus::BUSINESS_ERROR, 'user.phone.confirm_phone_not_equal_requested', [$curPhone, $requestedPhone]);
    }
}
