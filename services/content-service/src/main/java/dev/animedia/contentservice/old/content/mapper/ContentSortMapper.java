package dev.animedia.contentservice.old.content.mapper;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.ContentCommonProto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ContentSortMapper {
    public Sort toSort(ContentCommonProto.Sort sort) {
        String sortByStr = switch (sort.getSortBy()) {
            case RELEASE_DATE -> "release_date";
            case CREATED_AT -> "created_at";
            case UPDATED_AT -> "updated_at";
            case SORT, UNRECOGNIZED -> "sort";
        };
        if (sort.getSortType().equals(CommonProto.SortType.ASC)) return Sort.by(sortByStr).ascending();
        else return Sort.by(sortByStr).descending();
    }
}
