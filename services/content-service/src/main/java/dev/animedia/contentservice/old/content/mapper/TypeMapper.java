package dev.animedia.contentservice.old.content.mapper;

import dev.animedia.contentservice.old.content.model.ContentType;
import dev.animedia.grpc.core.ContentCommonProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TypeMapper {
    public ContentType toType(ContentCommonProto.ContentType grpcType) {
        return switch (grpcType) {
            case MOVIE -> ContentType.MOVIE;
            case SERIES -> ContentType.SERIES;
            case COMIC -> ContentType.COMIC;
            case BOOK -> ContentType.BOOK;
            case UNRECOGNIZED -> null;
        };
    }
    public List<ContentType> toTypeList(List<ContentCommonProto.ContentType> grpcTypes) {
        return grpcTypes.stream().map(this::toType).filter(Objects::nonNull).distinct().toList();
    }
    public ContentCommonProto.ContentType toGrpcType(ContentType type) {
        return switch (type) {
            case MOVIE -> ContentCommonProto.ContentType.MOVIE;
            case SERIES -> ContentCommonProto.ContentType.SERIES;
            case COMIC -> ContentCommonProto.ContentType.COMIC;
            case BOOK -> ContentCommonProto.ContentType.BOOK;
        };
    }
}
