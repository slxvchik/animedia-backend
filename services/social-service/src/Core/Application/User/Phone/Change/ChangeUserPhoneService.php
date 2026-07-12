<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\Change;


use Core\Application\PhoneCode\Exception\PhoneCodeDisabledException;
use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\User\Email\Change\ChangeUserPhoneNumberCommandDto;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Application\User\Shared\Exception\UserPhoneNumberExistsException;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\Shared\PhoneNumber\PhoneNumber;
use Core\Domain\Shared\PhoneNumber\PhoneValidatorInterface;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class ChangeUserPhoneService implements ChangeUserPhoneUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private UserCommandRepositoryInterface $userCommandRepository,
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneValidatorInterface $phoneValidator,
        private EventDispatcherInterface $eventDispatcher
    ) {}

    #[\Override]
    public function execute(string $userUuid, ?ChangeUserPhoneNumberCommandDto $newPhone): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        if ($newPhone === null) {
            if ($user->phone === null) {
                return;
            }
            $user->updatePhoneNumber(
                newPhoneNumber: null
            );
        } else {
            $phoneCode = $this->phoneCodeQueryRepository->findByPhoneCodeUuid(
                phoneCodeUuid: $newPhone->phoneCodeUuid
            );
            if ($phoneCode === null) {
                throw new PhoneCodeNotFoundException($newPhone->phoneCodeUuid);
            }

            if (!$phoneCode->active) {
                throw new PhoneCodeDisabledException($phoneCode->uuid->value);
            }

            $newPhoneNumber = PhoneNumber::create(
                phoneCodeUuid: $newPhone->phoneCodeUuid,
                phoneCode: $phoneCode->code,
                number: $newPhone->phoneNumber,
                validator: $this->phoneValidator
            );

            if (PhoneNumber::safeEquals($newPhoneNumber, $user->phone)) {
                return;
            }

            $phoneExists = $this->userQueryRepository->existsByPhoneAndPhoneNumberExcludeUserUuid(
                phoneCodeUuid: $newPhoneNumber->phoneCode->uuid->value,
                phoneNumber: $newPhoneNumber->phoneNumber,
                userUuid: $userUuid
            );
            if ($phoneExists) {
                throw new UserPhoneNumberExistsException(
                    phoneCode: $newPhoneNumber->code,
                    phoneNumber: $newPhoneNumber->phoneNumber
                );
            }

            $user->updatePhoneNumber(
                newPhoneNumber: $newPhoneNumber
            );
        }

        $this->userCommandRepository->update($user);

        foreach ($user->releaseEvents() as $eventInterface) {
            $this->eventDispatcher->dispatch($eventInterface);
        }
    }
}
