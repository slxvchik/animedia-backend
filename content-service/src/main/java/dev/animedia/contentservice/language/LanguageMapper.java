package dev.animedia.contentservice.language;

import dev.animedia.contentservice.language.dto.LanguageRequestDto;
import dev.animedia.contentservice.language.dto.LanguageResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageMapper {

	public Language toLanguage(LanguageRequestDto languageRequestDto) {
		Language language = new Language();
		language.setCode(languageRequestDto.code());
		language.setName(languageRequestDto.name());
		return language;
	}

	public LanguageResponseDto toLanguageResponseDto(Language language) {
		return new LanguageResponseDto(language.getCode(), language.getName());
	}

	public List<LanguageResponseDto> toLanguagesResponseDto(List<Language> languages) {
		return languages.stream().map(this::toLanguageResponseDto).toList();
	}
}
