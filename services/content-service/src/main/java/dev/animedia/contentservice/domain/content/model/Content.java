package dev.animedia.contentservice.domain.content.model;

import dev.animedia.contentservice.domain.status.model.Status;

import java.util.UUID;

public class Content {
	private UUID uuid;
	private String alias;
	private ContentType type;
	private Integer season;
	private Status status;
}
