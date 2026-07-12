package dev.animedia.contentservice.content.infrastracture.resolver.status;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StatusNotFoundException extends AppException {
    public StatusNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "statusId.not_found");
    }
    public StatusNotFoundException(UUID statusId) {
        super(AppExceptionStatus.NOT_FOUND, "statusId.not_found.extra", String.valueOf(statusId));
    }
    public StatusNotFoundException(List<UUID> statusIdList) {
        super(
            AppExceptionStatus.NOT_FOUND,
            "statusId.not_found.extra",
            statusIdList == null ? "" : statusIdList.stream().map(String::valueOf).collect(Collectors.joining(", "))
        );
    }
}
