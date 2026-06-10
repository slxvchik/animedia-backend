<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Command;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\PhoneCode\DTO\PhoneCodeResponseDto;
use Core\Application\PhoneCode\DTO\CreatePhoneCodeCommandDto;
use Core\Application\PhoneCode\Exception\PhoneCodeExistsException;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Command\CreatePhoneCodeUseCase;
use Core\Domain\Country\Repository\CountryQueryRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeCommandRepositoryInterface;
use Core\Domain\PhoneCode\Repository\PhoneCodeQueryRepositoryInterface;
use Core\Domain\Shared\IdentityGenerator\IdentityGeneratorInterface;

final readonly class CreatePhoneCodeService implements CreatePhoneCodeUseCase
{
    public function __construct(
        private PhoneCodeQueryRepositoryInterface $phoneCodeQueryRepository,
        private PhoneCodeCommandRepositoryInterface $phoneCodeCommandRepository,
        private CountryQueryRepositoryInterface $countryQueryRepository,
        private PhoneCodeApplicationMapperInterface $phoneCodeApplicationMapper,
        private IdentityGeneratorInterface $identityGenerator
    ) {}

    #[\Override]
    public function execute(CreatePhoneCodeCommandDto $phoneCodeRequestDto): PhoneCodeResponseDto
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

        $phoneUuid = $this->identityGenerator->generate();

        $phoneCode = $this->phoneCodeApplicationMapper->fromCreatePhoneCodeCommandDto($phoneCodeRequestDto, $phoneUuid);
        $created = $this->phoneCodeCommandRepository->create($phoneCode);

        return $this->phoneCodeApplicationMapper->toPhoneCodeResponseDto($created, $country);
    }
}
