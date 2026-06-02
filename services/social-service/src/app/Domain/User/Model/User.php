<?php

namespace App\Domain\User\Model;

use App\Domain\PhoneCode\Model\PhoneCode;

class User {
    private string $uuid;
    private string $email;
    private PhoneCode $phoneCode;
    private string $phoneNumber;
}