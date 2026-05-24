package dev.animedia.languageservice.infrastructure.config;

import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import dev.animedia.languageservice.application.service.*;
import dev.animedia.languageservice.application.usecase.*;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguageUseCaseConfig {
	@Bean
	public CreateLanguageUseCase createLanguageUseCase(
		LanguageCommandRepository languageCommandRepository,
		LanguageQueryRepository languageQueryRepository,
		LanguageApplicationMapper languageApplicationMapper
	) {
		return new CreateLanguageService(
			languageCommandRepository,
			languageQueryRepository,
			languageApplicationMapper
		);
	}

	@Bean
	public DeleteLanguageUseCase deleteLanguageUseCase(
		LanguageCommandRepository languageCommandRepository,
		LanguageQueryRepository languageQueryRepository
	) {
		return new DeleteLanguageService(
			languageCommandRepository,
			languageQueryRepository
		);
	}

	@Bean
	public GetLanguageUseCase getLanguageUseCase(
		LanguageQueryRepository languageQueryRepository,
		LanguageApplicationMapper languageApplicationMapper
	) {
		return new GetLanguageService(
			languageQueryRepository,
			languageApplicationMapper
		);
	}

	@Bean
	public SearchLanguageUseCase searchLanguageUseCase(
		LanguageQueryRepository languageQueryRepository,
		LanguageApplicationMapper languageApplicationMapper
	) {
		return new SearchLanguageService(
			languageQueryRepository,
			languageApplicationMapper
		);
	}

	@Bean
	public UpdateLanguageUseCase updateLanguageUseCase(
		LanguageCommandRepository languageCommandRepository,
		LanguageQueryRepository languageQueryRepository,
		LanguageApplicationMapper languageApplicationMapper
	) {
		return new UpdateLanguageService(
			languageCommandRepository,
			languageQueryRepository,
			languageApplicationMapper
		);
	}
}
