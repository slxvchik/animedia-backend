package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.model.ContentType;

import java.time.LocalDate;
import java.util.List;

public interface CommonSearchRequestDto {
	String alias();
	String title();
	List<ContentType> types();
	List<Integer> seasons();
	List<Long> contentStatusIds();
	LocalDate releaseFrom();
	LocalDate releaseTo();
	List<String> languageCodes();
	List<Long> genreIds();
}
