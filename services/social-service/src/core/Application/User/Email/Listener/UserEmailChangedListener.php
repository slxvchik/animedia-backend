<?php

namespace Core\Application\User\Email\Listener;

use Core\Application\User\Email\SendConfirm\SendUserEmailConfirmMailUseCase;

final readonly class UserEmailChangedListener
{
    public function __construct(
        private SendUserEmailConfirmMailUseCase $sendUserEmailConfirmMailUseCase
    ) {}

    public function execute(string $userUuid): void
    {
        $this->sendUserEmailConfirmMailUseCase->execute(
            userUuid: $userUuid
        );
    }
}
