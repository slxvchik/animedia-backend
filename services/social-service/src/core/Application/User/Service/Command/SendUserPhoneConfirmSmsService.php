<?php

namespace Core\Application\User\Service\Command;

use Core\Application\User\Exception\UserNotFoundException;
use Core\Application\User\Exception\UserPhoneAbsentException;
use Core\Application\User\UseCase\Command\SendUserPhoneConfirmSmsUseCase;
use Core\Domain\PhoneVerificationToken\Entity\PhoneVerificationToken;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenCommandRepository;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenQueryRepository;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\Shared\Service\SmsSenderInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;

final readonly class SendUserPhoneConfirmSmsService implements SendUserPhoneConfirmSmsUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface $userQueryRepository,
        private PhoneVerificationTokenQueryRepository $phoneVerificationTokenQueryRepository,
        private PhoneVerificationTokenCommandRepository $phoneVerificationTokenCommandRepository,
        private IdentityGeneratorInterface $identityGenerator,
        private SmsSenderInterface $smsSender
    ) {}

    #[\Override]
    public function execute(string $userUuid): void
    {
        $user = $this->userQueryRepository->findByUserUuid($userUuid);
        if ($user === null) {
            throw new UserNotFoundException($userUuid);
        }

        if ($user->phone === null) {
            throw new UserPhoneAbsentException();
        }

        if ($user->phoneConfirmed) {
            return;
        }

        // TODO: add anti-spam logic
        $userPhoneVerificationTokenList = $this->phoneVerificationTokenQueryRepository->findByUserUuid($userUuid);
        if (count($userPhoneVerificationTokenList) > 5) {
            // throw
        }

        $newUserPhoneVerificationToken = PhoneVerificationToken::createNew(
            userUuid: $userUuid,
            phoneNumber: $user->phone,
            identityGenerator: $this->identityGenerator
        );

        $this->phoneVerificationTokenCommandRepository->create($newUserPhoneVerificationToken);

        $this->smsSender->send(
            toPhone: $user->phone,
            template: 'user_phone_confirmation',
            localeLanguageIsoCode: $user->localeLanguageIsoCode,
            vars: ['code' => (string)$newUserPhoneVerificationToken->code]
        );
    }
}
