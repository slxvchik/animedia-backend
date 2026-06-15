<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\Exception\UserPhoneAbsentException;
use Core\Application\User\Exception\UserPhoneConfirmException;
use Core\Application\User\Exception\UserPhoneConfirmTokenInvalidCodeException;
use Core\Application\User\Exception\UserPhoneConfirmTokenAttemptsException;
use Core\Application\User\Exception\UserPhoneConfirmTokenExpiredException;
use Core\Application\User\Exception\UserPhoneConfirmTokenNotFoundException;
use Core\Application\User\Exception\UserPhoneConfirmTokenUsedException;
use Core\Application\User\UseCase\Command\ConfirmUserPhoneUseCase;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenCommandRepository;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenQueryRepository;
use Core\Domain\Shared\ValueObject\PhoneNumber;
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

        if (!PhoneNumber::safeEquals($user->phone, $lastUserToken->phoneNumber)) {
            throw new UserPhoneConfirmException(
                curNumber: $user->phone,
                requestedNumber: $lastUserToken->phoneNumber
            );
        }

        if ($lastUserToken->isUsed) {
            throw new UserPhoneConfirmTokenUsedException();
        }

        if ($lastUserToken->isExpired()) {
            throw new UserPhoneConfirmTokenExpiredException();
        }

        if ($lastUserToken->attempts > 3) {
            throw new UserPhoneConfirmTokenAttemptsException();
        }

        $lastUserToken->increaseAttempts();
        $this->phoneVerificationTokenCommandRepository->update($lastUserToken);

        if ($lastUserToken->code !== $code) {
            throw new UserPhoneConfirmTokenInvalidCodeException();
        }

        $lastUserToken->deactivate();
        $this->phoneVerificationTokenCommandRepository->update($lastUserToken);

        $user->confirmPhoneNumber();
        $this->userCommandRepository->update($user);
    }
}
