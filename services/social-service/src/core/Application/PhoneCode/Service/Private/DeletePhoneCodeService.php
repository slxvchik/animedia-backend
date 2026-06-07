<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Private;

use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\PhoneCode\UseCase\Private\DeletePhoneCodeUseCase;
use Core\Domain\PhoneCode\Repository\PhoneCodeCommandRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class DeletePhoneCodeService implements DeletePhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeCommandRepositoryInterface $phoneCodeCommandRepository
    ) {}

    #[\Override]
    public function execute(string $countryIsoCode): void
    {
        $phoneCode = $this->phoneCodeQueryRepository->findByCountryIsoCode($countryIsoCode);
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($countryIsoCode);
        }
        $this->phoneCodeCommandRepository->delete($countryIsoCode);
    }
}
