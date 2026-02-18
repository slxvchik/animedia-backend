package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.mapper.ContentStatusMapper;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusAliasExistsException;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusNotFoundException;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusCommandService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusQueryService;
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
		if (aliasExists) throw new ContentStatusAliasExistsException();

		ContentStatus contentStatus = contentStatusMapper.toContentStatus(contentStatusRequestDto);
		var savedContentStatus = contentStatusRepository.save(contentStatus);

		return contentStatusMapper.toContentStatusResponseDto(savedContentStatus);
	}

	@Override
	public ContentStatusResponseDto update(Long id, ContentStatusRequestDto contentStatusRequestDto) {

		var contentStatus = contentStatusRepository.findById(id).orElseThrow(ContentStatusNotFoundException::new);
		if (contentStatus.getAlias().equals(contentStatusRequestDto.alias())) return contentStatusMapper.toContentStatusResponseDto(contentStatus);

		var aliasExists = contentStatusQuery.existsByAlias(contentStatusRequestDto.alias());
		if (aliasExists) throw new ContentStatusAliasExistsException();

		contentStatus.setAlias(contentStatusRequestDto.alias());
		var savedContentStatus = contentStatusRepository.save(contentStatus);

		return contentStatusMapper.toContentStatusResponseDto(savedContentStatus);
	}

	@Override
	public void delete(Long id) {
		var contentStatusExists = contentStatusQuery.existsById(id);
		if (!contentStatusExists) throw new ContentStatusNotFoundException();
		contentStatusRepository.deleteById(id);
	}
}
