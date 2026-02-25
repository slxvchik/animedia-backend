package dev.animedia.contentservice.contentstatus.repository;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentStatusRepository extends JpaRepository<ContentStatus, Long>, JpaSpecificationExecutor<ContentStatus> {

    @Query("SELECT DISTINCT new dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto(" +
            "cs.id, cs.alias, cst.id, cst.language.code, cst.name) " +
            "FROM ContentStatus as cs LEFT JOIN FETCH ContentStatusTranslation as cst " +
            "WHERE (COALESCE(:ids, NULL) IS NULL OR cs.id IN :ids) " +
            "AND (COALESCE(:languageCodes, NULL) IS NULL OR cst.language.code IN :languageCodes) " +
            "AND (COALESCE(:aliases, NULL) IS NULL OR cs.alias IN :aliases) " +
            "AND (COALESCE(:names, NULL) IS NULL OR cst.name IN :names)")
    Page<ContentStatusWithTranslationResponseDto> search(
        @Param("ids") List<Long> ids,
        @Param("languageCodes") List<String> languageCodes,
        @Param("aliases") List<String> aliases,
        @Param("names") List<String> names,
        Pageable pageable
    );

	boolean existsByAlias(String alias);
    boolean existsByAliasAndIdIsNot(String alias, Long id);
}
