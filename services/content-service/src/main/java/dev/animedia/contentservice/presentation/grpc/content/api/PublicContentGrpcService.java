package dev.animedia.contentservice.presentation.grpc.content.api;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.usecase.GetContentByDetailsUseCase;
import dev.animedia.contentservice.application.content.usecase.SearchContentUseCase;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.presentation.grpc.content.mapper.ContentTypeGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.content.mapper.PublicContentGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.core.PublicContentProto.GetContentByDetailsRequest;
import dev.animedia.grpc.core.PublicContentProto.PublicContentResponse;
import dev.animedia.grpc.core.PublicContentProto.PublicSearchRequest;
import dev.animedia.grpc.core.PublicContentProto.PublicSearchResponse;
import dev.animedia.grpc.core.PublicContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;

@GrpcService
public class PublicContentGrpcService extends PublicContentServiceGrpc.PublicContentServiceImplBase {
    private final ProtoPaginationMapper protoPaginationMapper;
	private final PublicContentGrpcMapper publicContentGrpcMapper;
	private final ContentTypeGrpcMapper contentTypeGrpcMapper;

    private final SearchContentUseCase searchContentUseCase;
    private final GetContentByDetailsUseCase getContentByDetailsUseCase;

	@Autowired
	public PublicContentGrpcService(
        ProtoPaginationMapper protoPaginationMapper,
	    PublicContentGrpcMapper publicContentGrpcMapper,
		ContentTypeGrpcMapper contentTypeGrpcMapper,
	    SearchContentUseCase searchContentUseCase,
	    GetContentByDetailsUseCase getContentByDetailsUseCase
	) {
        this.protoPaginationMapper = protoPaginationMapper;
	    this.publicContentGrpcMapper = publicContentGrpcMapper;
		this.contentTypeGrpcMapper = contentTypeGrpcMapper;
		this.searchContentUseCase = searchContentUseCase;
	    this.getContentByDetailsUseCase = getContentByDetailsUseCase;
	}

    @Override
    public void search(
        PublicSearchRequest request,
        StreamObserver<PublicSearchResponse> responseObserver
    ) {
	    String languageCode = LanguageInterceptor.getLanguageCode();

		ContentSearchDto contentSearchDto = publicContentGrpcMapper.toContentSearchCriteria(
			request,
		    languageCode
	    );
	    Pageable domainPageable = protoPaginationMapper.toDomainPageable(
			request.getPagination(),
		    Set.of("alias", "season", "releaseDate", "sortOrder", "translations.title")
	    );

		Page<ContentDto> contentDtoPage = searchContentUseCase.search(
			contentSearchDto,
			domainPageable
		);

		PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(contentDtoPage);
        List<PublicContentResponse> publicContentResponseList = contentDtoPage.content()
		        .stream()
                .map(publicContentGrpcMapper::toPublicContentResponse)
                .toList();

		responseObserver.onNext(
            publicContentGrpcMapper.toPublicSearchResponse(
				publicContentResponseList,
	            paginationResponse
            )
		);
        responseObserver.onCompleted();
    }

    @Override
    public void get(
        GetContentByDetailsRequest request,
        StreamObserver<PublicContentResponse> responseObserver
    ) {
		String languageCode = LanguageInterceptor.getLanguageCode();

		ContentDto contentDto = getContentByDetailsUseCase.get(
			request.getAlias(),
            contentTypeGrpcMapper.toContentType(
				request.getType()
            ),
	        request.getSeason(),
	        languageCode,
	        true
        );

		responseObserver.onNext(
			publicContentGrpcMapper.toPublicContentResponse(contentDto)
		);
		responseObserver.onCompleted();
    }
}
