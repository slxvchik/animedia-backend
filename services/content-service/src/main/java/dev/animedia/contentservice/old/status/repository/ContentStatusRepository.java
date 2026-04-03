package dev.animedia.contentservice.old.status.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.old.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.old.status.model.ContentStatus;

@Repository
public interface ContentStatusRepository extends JpaRepository<ContentStatus, Long>, JpaSpecificationExecutor<ContentStatus> {

    @Query("SELECT DISTINCT new dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto(" +
            "cs.id, cs.alias, cst.id, cst.languageCode, cst.name) " +
            "FROM ContentStatus as cs LEFT JOIN FETCH ContentStatusTranslation as cst " +
            "WHERE (:ids IS NULL OR cs.id IN :ids) " +
            "AND (:languageCodes IS NULL OR cst.languageCode IN :languageCodes) " +
            "AND (:alias IS NULL OR LOWER(cs.alias) LIKE LOWER(:alias)) " +
            "AND (:name IS NULL OR LOWER(cst.name) LIKE LOWER(:name))")
    Page<ContentStatusWithTranslationResponseDto> search(
        @Param("ids") List<Long> ids,
        @Param("languageCodes") List<String> languageCodes,
        @Param("alias") String alias,
        @Param("name") String name,
        Pageable pageable
    );

	boolean existsByAlias(String alias);
    boolean existsByAliasAndIdIsNot(String alias, Long id);

    @Query("SELECT DISTINCT new dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto(" +
            "cs.id, cs.alias, cst.id, cst.languageCode, cst.name) " +
            "FROM ContentStatus as cs LEFT JOIN FETCH ContentStatusTranslation as cst " +
            "WHERE cs.id IN :ids " +
            "AND cst.languageCode = :languageCode ")
    List<ContentStatusWithTranslationResponseDto> findAllByIdAndLanguageCode(
        @Param("ids") List<Long> ids,
        @Param("languageCode") String languageCode
    );
}
