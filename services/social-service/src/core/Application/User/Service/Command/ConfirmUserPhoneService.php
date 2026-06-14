<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\UseCase\Command\ConfirmUserPhoneUseCase;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenQueryRepository;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\Shared\ValueObject\PhoneNumber;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class ConfirmUserPhoneService implements ConfirmUserPhoneUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private PhoneVerificationTokenQueryRepository $phoneVerificationTokenQueryRepository,
        private IdentityGeneratorInterface $identityGenerator
    ) {}

    #[\Override]
    public function execute(string $userUuid, PhoneNumber $phoneNumber, int $code): void
    {
        // TODO: Implement execute() method.
    }
}
