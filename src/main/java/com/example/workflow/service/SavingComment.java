package com.example.workflow.service;

import com.example.workflow.domain.Comment.Comment;
import com.example.workflow.domain.Comment.CommentRepository;
import com.example.workflow.domain.Film.FilmRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;


@Named
public class SavingComment implements JavaDelegate {

    private final FilmRepository filmRepository;
    private final CommentRepository commentRepository;

    public SavingComment(FilmRepository filmRepository, CommentRepository commentRepository) {
        this.filmRepository = filmRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    @Transactional
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String filmName = (String) delegateExecution.getVariable("film");
        String comment = (String) delegateExecution.getVariable("comment");
        Integer rating = Integer.parseInt(delegateExecution.getVariable("rating").toString());
        Long userId = Long.parseLong(delegateExecution.getVariable("userId").toString());
        Long filmId = filmRepository.getFilmByName(filmName).getId();

        Boolean isEditing = (Boolean) delegateExecution.getVariable("edit");
        if (isEditing) { commentRepository.deleteByUserIdAndFilmId(userId, filmId); }
        commentRepository.save(new Comment(comment, rating, null, userId, filmId));
    }
}
