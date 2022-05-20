package com.example.workflow.service;

import com.example.workflow.domain.Comment.Comment;
import com.example.workflow.domain.Comment.CommentRepository;
import com.example.workflow.domain.Film.FilmRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.List;

@Named
public class FindingComments implements JavaDelegate {

    private final CommentRepository commentRepository;

    public FindingComments(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Comment> comments = commentRepository.findAllByIsActive(null);
        delegateExecution.setVariable("list", comments);
    }
}
