<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Query;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\Exception\PhoneCodeNotFoundException;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Query\GetPhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class GetPhoneCodeService implements GetPhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper
    ) {}

    #[\Override]
    public function execute(string $phoneCodeUuid): PhoneCodeResponseDto
    {
        $phoneCode = $this->phoneCodeQueryRepository->findByPhoneCodeUuid(
            phoneCodeUuid: $phoneCodeUuid
        );
        if ($phoneCode === null) {
            throw new PhoneCodeNotFoundException($phoneCodeUuid);
        }

        $country = $this->countryQueryRepository->findByIsoCode($phoneCodeUuid);
        if ($country === null) {
            throw new CountryNotFoundException($phoneCodeUuid);
        }

        return $this->phoneCodeApplicationMapper->toPhoneCodeResponseDto(
            phoneCode: $phoneCode,
            country: $country
        );
    }
}
