<?php

declare(strict_types=1);

namespace Core\Application\User\Phone\SendConfirm;

use Core\Application\User\Phone\Shared\Exception\UserPhoneAbsentException;
use Core\Application\User\Phone\Shared\Exception\UserPhoneConfirmTokenLimitException;
use Core\Application\User\Shared\Exception\UserNotFoundException;
use Core\Domain\PhoneVerificationToken\Entity\PhoneVerificationToken;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenCommandRepository;
use Core\Domain\PhoneVerificationToken\Repository\PhoneVerificationTokenQueryRepository;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;
use Core\Domain\Shared\Service\SmsSenderInterface;
use Core\Domain\User\Repository\UserQueryRepositoryInterface;
use DateTimeImmutable;

final readonly class SendUserPhoneConfirmSmsService implements SendUserPhoneConfirmSmsUseCase
{
    public function __construct(
        private UserQueryRepositoryInterface            $userQueryRepository,
        private PhoneVerificationTokenQueryRepository   $phoneVerificationTokenQueryRepository,
        private PhoneVerificationTokenCommandRepository $phoneVerificationTokenCommandRepository,
        private IdentityGeneratorInterface              $identityGenerator,
        private SmsSenderInterface                      $smsSender
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

        $since = DateTimeImmutable::createFromTimestamp(time() - 60 * 60);
        $tokenCountForLastHour = $this->phoneVerificationTokenQueryRepository->countRecentTokensByUserUuid(
            userUuid: $userUuid,
            since: $since
        );
        if ($tokenCountForLastHour > 5) {
             throw new UserPhoneConfirmTokenLimitException();
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
