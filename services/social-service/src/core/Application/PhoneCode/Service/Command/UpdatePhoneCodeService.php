<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Command;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\PhoneCode\DTO\UpdatePhoneCodeCommandDto;
use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\PhoneCode\UseCase\Command\UpdatePhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeCommandRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class UpdatePhoneCodeService implements UpdatePhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeCommandRepositoryInterface $phoneCodeCommandRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
    ) {}

    #[\Override]
    public function execute(UpdatePhoneCodeCommandDto $phoneCodeRequestDto): void
    {
        $phoneCode = $this->phoneCodeQueryRepository->findByPhoneCodeUuid(
            phoneCodeUuid: $phoneCodeRequestDto->uuid
        );
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($phoneCodeRequestDto->uuid);
        }

        $country = $this->countryQueryRepository->findByIsoCode($phoneCodeRequestDto->countryIsoCode);
        if ($country === null) {
            throw new CountryNotFoundException($phoneCodeRequestDto->countryIsoCode);
        }

        $phoneCode->update(
            active: $phoneCodeRequestDto->isActive
        );

        $this->phoneCodeCommandRepository->update($phoneCode);
    }
}
