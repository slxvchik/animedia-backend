<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Command;

use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\PhoneCode\UseCase\Command\DeletePhoneCodeUseCase;
use Core\Domain\PhoneCode\Repository\PhoneCodeCommandRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class DeletePhoneCodeService implements DeletePhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeCommandRepositoryInterface $phoneCodeCommandRepository
    ) {}

    #[\Override]
    public function execute(string $phoneCodeUuid): void
    {
        $phoneCode = $this->phoneCodeQueryRepository->findByPhoneCodeUuid(
            phoneCodeUuid: $phoneCodeUuid
        );
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($phoneCodeUuid);
        }
        $this->phoneCodeCommandRepository->delete($phoneCodeUuid);
    }
}
