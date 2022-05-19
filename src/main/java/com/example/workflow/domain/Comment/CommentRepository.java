package com.example.workflow.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentByUserIdAndFilmId(Long userId, Long filmId);

    Long deleteByUserIdAndFilmId(Long userId, Long filmId);
}
