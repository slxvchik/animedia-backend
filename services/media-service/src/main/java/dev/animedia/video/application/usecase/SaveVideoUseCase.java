package dev.animedia.video.application.usecase;

import dev.animedia.video.application.dto.SaveVideoDto;

import java.util.UUID;

public interface SaveVideoUseCase {
	UUID execute(SaveVideoDto dto);
}
