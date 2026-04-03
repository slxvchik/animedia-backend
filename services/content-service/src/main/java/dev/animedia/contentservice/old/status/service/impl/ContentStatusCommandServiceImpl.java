package dev.animedia.contentservice.old.status.service.impl;

import dev.animedia.contentservice.old.app.exception.AppException;
import dev.animedia.contentservice.old.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.old.status.ContentStatusConstants;
import dev.animedia.contentservice.old.status.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.old.status.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.old.status.mapper.ContentStatusMapper;
import dev.animedia.contentservice.old.status.model.ContentStatus;
import dev.animedia.contentservice.old.status.repository.ContentStatusRepository;
import dev.animedia.contentservice.old.status.service.ContentStatusCommandService;
import dev.animedia.contentservice.old.status.service.ContentStatusQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentStatusCommandServiceImpl implements ContentStatusCommandService {

	private final ContentStatusRepository contentStatusRepository;
	private final ContentStatusMapper contentStatusMapper;
	private final ContentStatusQueryService contentStatusQuery;

	@Autowired
	public ContentStatusCommandServiceImpl(
		ContentStatusRepository contentStatusRepository,
		ContentStatusMapper contentStatusMapper,
		ContentStatusQueryService contentStatusQuery
	) {
		this.contentStatusRepository = contentStatusRepository;
		this.contentStatusMapper = contentStatusMapper;
		this.contentStatusQuery = contentStatusQuery;
	}

	@Override
	public ContentStatusResponseDto create(ContentStatusRequestDto contentStatusRequestDto) {

		var aliasExists = contentStatusQuery.existsByAlias(contentStatusRequestDto.alias());
		if (aliasExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, ContentStatusConstants.CONTENT_STATUS_ALIAS_EXISTS_MESSAGE);

		ContentStatus contentStatus = contentStatusMapper.toContentStatus(contentStatusRequestDto);
		var savedContentStatus = contentStatusRepository.save(contentStatus);

		return contentStatusMapper.toContentStatusResponseDto(savedContentStatus);
	}

	@Override
	public ContentStatusResponseDto update(Long id, ContentStatusRequestDto contentStatusRequestDto) {

		var contentStatus = contentStatusRepository.findById(id)
			.orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE));
		if (contentStatus.getAlias().equals(contentStatusRequestDto.alias())) return contentStatusMapper.toContentStatusResponseDto(contentStatus);

		var aliasExists = contentStatusQuery.existsByAlias(contentStatusRequestDto.alias());
		if (aliasExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, ContentStatusConstants.CONTENT_STATUS_ALIAS_EXISTS_MESSAGE);

		contentStatus.setAlias(contentStatusRequestDto.alias());
		var savedContentStatus = contentStatusRepository.save(contentStatus);

		return contentStatusMapper.toContentStatusResponseDto(savedContentStatus);
	}

	@Override
	public void delete(Long id) {
		var contentStatusExists = contentStatusQuery.existsById(id);
		if (!contentStatusExists) throw new AppException(AppExceptionStatus.NOT_FOUND, ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE);
		contentStatusRepository.deleteById(id);
	}
}
