package dev.animedia.languageservice.language;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.language.LanguageProto;
import dev.animedia.languageservice.language.dto.LanguageRequestDto;
import dev.animedia.languageservice.language.dto.LanguageResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageMapper {

	public Language toLanguage(LanguageRequestDto languageRequestDto) {
		Language language = new Language();
		language.setCode(languageRequestDto.code());
		language.setName(languageRequestDto.name());
		language.setNativeName(languageRequestDto.nativeName());
		language.setActive(languageRequestDto.isActive());
		language.setDefault(languageRequestDto.isDefault());
		language.setSortOrder(languageRequestDto.sortOrder());
		language.setFlagEmoji(languageRequestDto.flagEmoji());
		return language;
	}

	public LanguageResponseDto toLanguageResponseDto(Language language) {
		return new LanguageResponseDto(
			language.getCode(),
			language.getName(),
			language.getNativeName(),
			language.getActive(),
			language.getDefault(),
			language.getSortOrder(),
			language.getFlagEmoji()
		);
	}

	public List<LanguageResponseDto> toLanguagesResponseDto(List<Language> languages) {
		return languages.stream().map(this::toLanguageResponseDto).toList();
	}

	public LanguageProto.LanguageResponse toProtoResponse(LanguageResponseDto languageResponseDto) {
		return LanguageProto.LanguageResponse.newBuilder()
			.setCode(languageResponseDto.code())
			.setName(languageResponseDto.name())
			.setNativeName(languageResponseDto.nativeName())
			.setIsActive(languageResponseDto.isActive())
			.setIsDefault(languageResponseDto.isDefault())
			.setSortOrder(languageResponseDto.sortOrder())
			.setFlagEmoji(languageResponseDto.flagEmoji())
			.build();
	}

	public LanguageProto.LanguageListResponse toProtoListResponse(List<LanguageResponseDto> languageResponseDtoList, CommonProto.PaginationResponse paginationResponse) {
		List<LanguageProto.LanguageResponse> languages = languageResponseDtoList.stream().map(this::toProtoResponse).toList();
		return LanguageProto.LanguageListResponse.newBuilder()
			.addAllItems(languages)
			.setPagination(paginationResponse)
			.build();
	}

	public LanguageRequestDto toLanguageRequestDto(LanguageProto.CreateLanguageRequest createLanguageRequest) {
		return new LanguageRequestDto(
			createLanguageRequest.getCode(),
			createLanguageRequest.getName(),
			createLanguageRequest.getNativeName(),
			createLanguageRequest.getIsActive(),
			createLanguageRequest.getIsDefault(),
			createLanguageRequest.getSortOrder(),
			createLanguageRequest.getFlagEmoji()
		);
	}

	public LanguageRequestDto toLanguageRequestDto(LanguageProto.UpdateLanguageRequest updateLanguageRequest) {
		return new LanguageRequestDto(
			updateLanguageRequest.getCode(),
			updateLanguageRequest.getName(),
			updateLanguageRequest.getNativeName(),
			updateLanguageRequest.getIsActive(),
			updateLanguageRequest.getIsDefault(),
			updateLanguageRequest.getSortOrder(),
			updateLanguageRequest.getFlagEmoji()
		);
	}
}
