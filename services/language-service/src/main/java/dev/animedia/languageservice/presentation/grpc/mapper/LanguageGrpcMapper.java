package dev.animedia.languageservice.presentation.grpc.mapper;

import dev.animedia.grpc.language.LanguageProto;
import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.dto.SearchLanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageGrpcMapper {
    private final PaginationGrpcMapper paginationMapper;

    @Autowired
    public LanguageGrpcMapper(PaginationGrpcMapper paginationMapper) {
        this.paginationMapper = paginationMapper;
    }

    public SearchLanguageDto toSearchLanguageDto(LanguageProto.SearchLanguageRequest request) {
        var pageable = paginationMapper.toPageable(request.getPagination());
        return new SearchLanguageDto(request.getCodesList(), request.getNamesList(), request.hasIsActive() ? request.getIsActive() : null, pageable);
    }

    public LanguageProto.LanguageResponse toProtoResponse(LanguageDto languageDto) {
        return LanguageProto.LanguageResponse.newBuilder()
            .setCode(languageDto.code())
            .setName(languageDto.name())
            .setIsActive(languageDto.isActive())
            .setIsDefault(languageDto.isDefault())
            .setSortOrder(languageDto.sortOrder())
            .setFlagEmoji(languageDto.flagEmoji())
            .build();
    }

    public LanguageDto toLanguageDto(LanguageProto.LanguageRequest request) {
        return new LanguageDto(
            request.getCode(),
            request.getName(),
            request.getIsActive(),
            request.getIsDefault(),
            request.getSortOrder(),
            request.getFlagEmoji()
        );
    }
}
