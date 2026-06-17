<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\Confirm;

use Core\Application\User\Phone\Shared\Exception\UserPhoneAbsentException;
use Core\Application\User\Phone\Shared\Exception\UserPhoneConfirmTokenNotFoundException;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenCommandRepository;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenQueryRepository;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class ConfirmUserPhoneService implements ConfirmUserPhoneUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private PhoneVerificationTokenQueryRepository $phoneVerificationTokenQueryRepository,
        private PhoneVerificationTokenCommandRepository $phoneVerificationTokenCommandRepository
    ) {}

    #[\Override]
    public function execute(string $userUuid, int $code): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        if ($user->phone === null) {
            throw new UserPhoneAbsentException();
        }

        $lastUserToken = $this->phoneVerificationTokenQueryRepository->findLastTokenByUserUuid($userUuid);
        if ($lastUserToken === null) {
            throw new UserPhoneConfirmTokenNotFoundException();
        }

        $lastUserToken->verify(
            userPhoneNumber: $user->phone,
            code: $code
        );

        $this->phoneVerificationTokenCommandRepository->update($lastUserToken);

        $user->confirmPhoneNumber();
        $this->userCommandRepository->update($user);
    }
}
