package dev.animedia.contentservice.presentation.grpc.genre.api;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.usecase.SearchGenreUseCase;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.presentation.grpc.genre.mapper.PublicGenreGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.genre.PublicGenreProto;
import dev.animedia.grpc.genre.PublicGenreServiceGrpc;
import dev.animedia.grpc.common.CommonProto;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;

@GrpcService
public class PublicGenreGrpcService extends PublicGenreServiceGrpc.PublicGenreServiceImplBase {
    private final PublicGenreGrpcMapper publicGenreGrpcMapper;
    private final ProtoPaginationMapper protoPaginationMapper;

    private final SearchGenreUseCase searchGenreUseCase;

    @Autowired
    public PublicGenreGrpcService(
        ProtoPaginationMapper protoPaginationMapper,
        SearchGenreUseCase searchGenreUseCase,
        PublicGenreGrpcMapper publicGenreGrpcMapper
    ) {
	    this.protoPaginationMapper = protoPaginationMapper;
	    this.searchGenreUseCase = searchGenreUseCase;
        this.publicGenreGrpcMapper = publicGenreGrpcMapper;
    }

    @Override
    public void search(
        PublicGenreProto.PublicSearchGenreRequest request,
        StreamObserver<PublicGenreProto.PublicSearchGenreResponse> responseObserver
    ) {
        String languageCode = LanguageInterceptor.getLanguageCode();

        GenreSearchDto genreSearchDto = publicGenreGrpcMapper.toGenreSearchDto(request, languageCode);
        Pageable domainPageable = protoPaginationMapper.toDomainPageable(
            request.getPagination(),
            Set.of("alias", "sortOrder", "translations.name")
        );

        Page<GenreDto> genreDtoPage = searchGenreUseCase.search(genreSearchDto, domainPageable);

        CommonProto.PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(genreDtoPage);
        List<PublicGenreProto.PublicGenreResponse> genreResponseList = genreDtoPage.content() != null
            ? genreDtoPage.content()
                .stream()
                .map(publicGenreGrpcMapper::toPublicGenreResponse)
                .toList()
            : List.of();

        responseObserver.onNext(
            PublicGenreProto.PublicSearchGenreResponse
                .newBuilder()
                .addAllGenres(genreResponseList)
                .setPagination(paginationResponse)
                .build()
        );
        responseObserver.onCompleted();
    }
}
