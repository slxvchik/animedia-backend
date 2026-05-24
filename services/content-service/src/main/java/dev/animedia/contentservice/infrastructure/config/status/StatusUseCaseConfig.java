package dev.animedia.contentservice.infrastructure.config.status;

import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.service.*;
import dev.animedia.contentservice.application.status.usecase.*;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.transactional.status.CreateStatusTransactionalDecorator;
import dev.animedia.contentservice.infrastructure.transactional.status.UpdateStatusTransactionalDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StatusUseCaseConfig {
	@Bean("createStatusUseCase")
	public CreateStatusUseCase createStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusCommandRepository statusCommandRepository,
		StatusQueryRepository statusQueryRepository
	) {
		return new CreateStatusService(
			statusApplicationMapper,
			statusCommandRepository,
			statusQueryRepository
		);
	}

	@Bean
	@Primary
	public CreateStatusUseCase createStatusFacade(
		CreateStatusUseCase createStatusUseCase
	) {
		return new CreateStatusTransactionalDecorator(
			createStatusUseCase
		);
	}

	@Bean("updateStatusUseCase")
	public UpdateStatusUseCase updateStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository,
		StatusCommandRepository statusCommandRepository
	) {
		return new UpdateStatusService(
			statusApplicationMapper,
			statusQueryRepository,
			statusCommandRepository
		);
	}

	@Bean
	@Primary
	public UpdateStatusUseCase updateStatusFacade(
		UpdateStatusUseCase updateStatusUseCase
	) {
		return new UpdateStatusTransactionalDecorator(
			updateStatusUseCase
		);
	}

	@Bean
	public DeleteStatusUseCase deleteStatusUseCase(
		StatusQueryRepository statusQueryRepository,
		StatusCommandRepository statusCommandRepository
	) {
		return new DeleteStatusService(
			statusQueryRepository,
			statusCommandRepository
		);
	}

	@Bean
	public DeleteStatusTranslationUseCase deleteStatusTranslationUseCase(
		StatusQueryRepository statusQueryRepository,
		StatusCommandRepository statusCommandRepository
	) {
		return new DeleteStatusTranslationService(
			statusQueryRepository,
			statusCommandRepository
		);
	}

	@Bean
	public GetStatusListUseCase getStatusListUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository
	) {
		return new GetStatusListService(
			statusApplicationMapper,
			statusQueryRepository
		);
	}

	@Bean
	public GetStatusUseCase getStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository
	) {
		return new GetStatusService(
			statusApplicationMapper,
			statusQueryRepository
		);
	}

	@Bean
	public SearchStatusUseCase searchStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository
	) {
		return new SearchStatusService(
			statusApplicationMapper,
			statusQueryRepository
		);
	}
}
