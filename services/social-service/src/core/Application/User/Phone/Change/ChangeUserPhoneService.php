<?php

namespace Core\Application\User\Phone\Change;


use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\User\Email\Change\ChangeUserPhoneNumberCommandDto;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Application\User\Shared\Exception\UserPhoneNumberExistsException;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\Shared\Service\PhoneValidatorInterface;
use Core\Domain\Shared\ValueObject\PhoneNumber;
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

            $newPhoneNumber = PhoneNumber::create(
                code: $phoneCode->code,
                number: $newPhone->phoneNumber,
                validator: $this->phoneValidator
            );

            if (PhoneNumber::safeEquals($newPhoneNumber, $user->phone)) {
                return;
            }

            $phoneExists = $this->userQueryRepository->existsByPhoneAndPhoneNumberExcludeUserUuid(
                phoneCode: $newPhoneNumber->code,
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

        foreach ($user->releaseEvents() as $event) {
            $this->eventDispatcher->dispatch($event);
        }
    }
}
