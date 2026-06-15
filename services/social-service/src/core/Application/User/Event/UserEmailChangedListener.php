<?php

namespace Core\Application\User\Event;

use Core\Application\User\UseCase\Command\SendUserEmailConfirmMailUseCase;

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
