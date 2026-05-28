package dev.animedia.contentservice.presentation.grpc.content.mapper;

import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.grpc.core.ContentCommonProto.Type;
import org.springframework.stereotype.Component;

@Component
public class ContentTypeGrpcMapper {
	public ContentType toContentType(
		Type protoContentType
	) {
		return switch (protoContentType) {
			case SERIES -> ContentType.SERIES;
			case MOVIE -> ContentType.MOVIE;
			case COMIC -> ContentType.COMIC;
			case BOOK -> ContentType.BOOK;
			case UNRECOGNIZED -> null;
		};
	}

	public Type toProtoContentType(
		ContentType contentType
	) {
		return switch (contentType) {
			case SERIES -> Type.SERIES;
			case MOVIE -> Type.MOVIE;
			case COMIC -> Type.COMIC;
			case BOOK -> Type.BOOK;
		};
	}
}
