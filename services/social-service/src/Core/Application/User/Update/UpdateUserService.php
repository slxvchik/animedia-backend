<?php

declare(strict_types=1);

namespace Core\Application\User\Update;

use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\User\Shared\Exception\UserEmailExistsException;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Application\User\Shared\Exception\UserPhoneNumberExistsException;
use Core\Application\User\Shared\Exception\UserUsernameExistsException;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;
use Core\Domain\Shared\Event\EventDispatcherInterface;
use Core\Domain\Shared\PhoneNumber\PhoneNumber;
use Core\Domain\Shared\PhoneNumber\PhoneValidatorInterface;
use Core\Domain\User\Entity\User;
use Core\Domain\User\Repository\UserCommandRepositoryInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class UpdateUserService implements UpdateUserUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface      $userQueryRepository,
        private UserCommandRepositoryInterface    $userCommandRepository,
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneValidatorInterface           $phoneValidator,
        private EventDispatcherInterface          $eventDispatcher
    ) {}

    #[\Override]
    public function execute(UpdateUserCommandDto $userDto): void
    {
        $user = $this->userQueryRepository->findByUserUuid(
            userUuid: $userDto->userUuid
        );
        if ($user === null) {
            throw new UserNotFoundException(
                uuid: $userDto->userUuid
            );
        }

        $this->updateUserEmail(
            user: $user,
            userDto: $userDto
        );

        $this->updateUserPhoneNumber(
            user: $user,
            userDto: $userDto
        );

        $usernameExists = $this->userQueryRepository->existsByUsernameAndUsernameCodeExcludeUserUuid(
            username: $userDto->username,
            usernameCode: $userDto->usernameCode,
            userUuid: $userDto->userUuid
        );
        if ($usernameExists) {
            throw new UserUsernameExistsException(
                username: $userDto->username,
                usernameCode: $userDto->usernameCode,
            );
        }

        $user->update(
            username: $userDto->username,
            usernameCode: $userDto->usernameCode,
            localeLanguageIsoCode: $userDto->localeLanguageIsoCode,
            firstName: $userDto->firstName,
            lastName: $userDto->lastName,
            middleName: $userDto->middleName,
            languageIsoCodeList: $userDto->languageIsoCodeList,
            countryIsoCode: $userDto->countryIsoCode,
            imageUuid: $userDto->imageUuid,
            color: $userDto->color,
            description: $userDto->description
        );

        $this->userCommandRepository->update(
            user: $user
        );

        foreach ($user->releaseEvents() as $eventInterface) {
            $this->eventDispatcher->dispatch(
                eventInterface: $eventInterface
            );
        }
    }

    private function updateUserEmail(User $user, UpdateUserCommandDto $userDto): void
    {
        if ($user->email === $userDto->email) {
            return;
        }

        $emailExists = $this->userQueryRepository->existsByEmailExcludeUserUuid($userDto->email, $user->uuid->value);
        if ($emailExists) {
            throw new UserEmailExistsException(
                email: $userDto->email
            );
        }

        $user->updateEmail(
            newEmail: $userDto->email,
            emailConfirmed: $userDto->emailConfirmed
        );
    }

    private function updateUserPhoneNumber(User $user, UpdateUserCommandDto $userDto): void
    {
        if ($userDto->phoneNumberDto === null) {
            $user->updatePhoneNumber(
                newPhoneNumber: null
            );
            return;
        }

        $phoneCode = $this->phoneCodeQueryRepository->findByPhoneCodeUuid(
            phoneCodeUuid: $userDto->phoneNumberDto->phoneCodeUuid
        );
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($userDto->phoneNumberDto->phoneCodeUuid);
        }

        $newPhoneNumber = PhoneNumber::create(
            phoneCodeUuid: $phoneCode->code,
            phoneCode: $phoneCode->code,
            number: $userDto->phoneNumberDto->phoneNumber,
            validator: $this->phoneValidator
        );
        if (PhoneNumber::safeEquals($user->phone, $newPhoneNumber)) {
            return;
        }

        $phoneExists = $this->userQueryRepository->existsByPhoneAndPhoneNumberExcludeUserUuid(
            phoneCodeUuid: $newPhoneNumber->phoneCode->uuid->value,
            phoneNumber: $newPhoneNumber->phoneNumber,
            userUuid: $user->uuid->value
        );
        if ($phoneExists) {
            throw new UserPhoneNumberExistsException(
                phoneCode: $newPhoneNumber->phoneCode->code,
                phoneNumber: $newPhoneNumber->phoneNumber
            );
        }

        $user->updatePhoneNumber(
            newPhoneNumber: $newPhoneNumber,
            phoneConfirmed: $userDto->phoneConfirmed
        );
    }
}
