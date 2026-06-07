<?php

declare(strict_types=1);

namespace Core\Application\PhoneCode\Service\Command;

use Core\Application\Country\Exception\CountryNotFoundException;
use Core\Application\PhoneCode\DTO\PhoneCodePrivateResponseDto;
use Core\Application\PhoneCode\DTO\CommandPhoneCodeRequestDto;
use Core\Application\PhoneCode\Exception\PhoneCodeExistsException;
use Core\Application\PhoneCode\Mapper\PhoneCodeApplicationMapperInterface;
use Core\Application\PhoneCode\UseCase\Command\CreatePhoneCodeUseCase;
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
    public function execute(CommandPhoneCodeRequestDto $phoneCodeRequestDto): PhoneCodePrivateResponseDto
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
