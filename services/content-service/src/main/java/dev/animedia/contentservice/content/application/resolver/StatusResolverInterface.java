package dev.animedia.contentservice.content.application.resolver;

import dev.animedia.contentservice.content.application.dto.status.StatusDto;

import java.util.List;
import java.util.Set;

public interface StatusResolverInterface {
	List<StatusDto> resolve(Set<String> statusIdSet);
}
