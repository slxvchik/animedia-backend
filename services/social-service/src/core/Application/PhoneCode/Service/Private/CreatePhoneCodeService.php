<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Private;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\Country\Mapper\CountryApplicationMapperInterface;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeRequestDto;
use Core\Application\PhoneCode\DTO\Private\PhoneCodeResponseDto;
use Core\Application\PhoneCode\Exception\PhoneCodeExistsException;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Private\CreatePhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeCommandRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;

final readonly class CreatePhoneCodeService implements CreatePhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeCommandRepositoryInterface $phoneCodeCommandRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplication
    ) {}

    #[\Override]
    public function execute(PhoneCodeRequestDto $phoneCodeRequestDto): PhoneCodeResponseDto
    {
        $country = $this->countryQueryRepository->findByIsoCode($phoneCodeRequestDto->countryIsoCode);
        if ($country === null) {
            throw new CountryNotFoundException($phoneCodeRequestDto->countryIsoCode);
        }

        $phoneCodeExists = $this->phoneCodeQueryRepository->existsByCountryIsoCodeAndPhoneCode(
            countryIsoCode: $phoneCodeRequestDto->countryIsoCode,
            phoneCode: $phoneCodeRequestDto->phoneCode
        );
        if ($phoneCodeExists) {
            throw new PhoneCodeExistsException();
        }

        $phoneCode = $this->phoneCodeApplication->toPhoneCode($phoneCodeRequestDto);
        $created = $this->phoneCodeCommandRepository->create($phoneCode);

        return $this->phoneCodeApplication->toPrivatePhoneCodeResponseDto($created, $country);
    }
}
