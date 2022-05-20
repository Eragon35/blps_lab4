package com.example.workflow.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentByUserIdAndFilmId(Long userId, Long filmId);

    Comment findCommentById(Long id);

    Long deleteByUserIdAndFilmId(Long userId, Long filmId);

    Long deleteAllByIsActive(Boolean param);

    @Query(value = "select max(c.id) from Comment c where c.isActive is null ")
    Integer findLastUncheckedCommentId();


    @Modifying
    @Query("update Comment c set c.isActive = :isActive where c.id = :id")
    int updateIsActive(@Param("id") Long id, @Param(value = "isActive") Boolean isActive);
}
