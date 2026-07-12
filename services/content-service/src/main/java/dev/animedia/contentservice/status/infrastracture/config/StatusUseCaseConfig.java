package dev.animedia.contentservice.status.infrastracture.config;

import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.status.application.service.GetStatusListService;
import dev.animedia.contentservice.status.application.service.IndexAllStatusService;
import dev.animedia.contentservice.status.application.service.admin.CreateStatusService;
import dev.animedia.contentservice.status.application.service.admin.DeleteStatusService;
import dev.animedia.contentservice.status.application.service.admin.GetStatusDetailService;
import dev.animedia.contentservice.status.application.service.admin.UpdateStatusService;
import dev.animedia.contentservice.status.application.usecase.GetStatusListUseCase;
import dev.animedia.contentservice.status.application.usecase.IndexAllStatusUseCase;
import dev.animedia.contentservice.status.domain.repository.StatusCommandRepository;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;
import dev.animedia.contentservice.status.infrastracture.transactional.CreateStatusTransactionalDecorator;
import dev.animedia.contentservice.status.infrastracture.transactional.UpdateStatusTransactionalDecorator;
import dev.animedia.contentservice.status.application.usecase.admin.CreateStatusUseCase;
import dev.animedia.contentservice.status.application.usecase.admin.DeleteStatusUseCase;
import dev.animedia.contentservice.status.application.usecase.admin.GetStatusDetailUseCase;
import dev.animedia.contentservice.status.application.usecase.admin.UpdateStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StatusUseCaseConfig {
	@Bean("createStatusUseCase")
	public CreateStatusUseCase createStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusCommandRepository statusCommandRepository,
		StatusQueryRepository statusQueryRepository,
		EventDispatcherInterface eventDispatcherInterface
	) {
		return new CreateStatusService(
			statusApplicationMapper,
			statusCommandRepository,
			statusQueryRepository,
			eventDispatcherInterface
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
		StatusCommandRepository statusCommandRepository,
		EventDispatcherInterface eventDispatcherInterface
	) {
		return new UpdateStatusService(
			statusApplicationMapper,
			statusQueryRepository,
			statusCommandRepository,
			eventDispatcherInterface
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
		StatusCommandRepository statusCommandRepository,
		EventDispatcherInterface eventDispatcherInterface
	) {
		return new DeleteStatusService(
			statusQueryRepository,
			statusCommandRepository,
			eventDispatcherInterface
		);
	}

	@Bean
	public GetStatusDetailUseCase getStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository
	) {
		return new GetStatusDetailService(
			statusApplicationMapper,
			statusQueryRepository
		);
	}

	@Bean
	public IndexAllStatusUseCase getAllStatusUseCase(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository
	) {
		return new IndexAllStatusService(
			statusApplicationMapper,
			statusQueryRepository
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
}
