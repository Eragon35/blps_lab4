package com.example.workflow;

import com.example.workflow.domain.Comment.Comment;
import com.example.workflow.domain.Comment.CommentRepository;
import com.example.workflow.domain.Film.FilmRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class FindingComment implements JavaDelegate {

    private final FilmRepository filmRepository;
    private final CommentRepository commentRepository;

    public FindingComment(FilmRepository filmRepository, CommentRepository commentRepository) {
        this.filmRepository = filmRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // check if this user left a comment
        String filmName = (String) delegateExecution.getVariable("film");
        Long userId = Long.parseLong(delegateExecution.getVariable("userId").toString());
        System.out.println(userId);
        Long filmId = filmRepository.getFilmByName(filmName).getId();

        //TODO: add check if filmId & userId is not null

        Comment comment = commentRepository.findCommentByUserIdAndFilmId(userId, filmId);
        if (comment != null) {
            delegateExecution.setVariable("hasComment", true);
        } else {
            delegateExecution.setVariable("hasComment", false);
        }
    }
}
