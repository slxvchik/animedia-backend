package dev.animedia.contentservice.content.presentation.mapper;

import dev.animedia.contentservice.content.domain.model.ContentType;
import dev.animedia.grpc.content.shared.v1.ContentCommonProto;
import org.springframework.stereotype.Component;

@Component
public class ContentTypeMapperGrpc {
	public ContentType toContentType(
		ContentCommonProto.Type protoContentType
	) {
		return switch (protoContentType) {
			case SERIES -> ContentType.SERIES;
			case MOVIE -> ContentType.MOVIE;
			case COMIC -> ContentType.COMIC;
			case BOOK -> ContentType.BOOK;
			case UNRECOGNIZED -> null;
		};
	}

	public ContentCommonProto.Type toProtoContentType(
		ContentType contentType
	) {
		return switch (contentType) {
			case SERIES -> ContentCommonProto.Type.SERIES;
			case MOVIE -> ContentCommonProto.Type.MOVIE;
			case COMIC -> ContentCommonProto.Type.COMIC;
			case BOOK -> ContentCommonProto.Type.BOOK;
		};
	}
}
