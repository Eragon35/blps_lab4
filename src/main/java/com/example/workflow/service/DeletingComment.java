package com.example.workflow.service;

import com.example.workflow.domain.Comment.CommentRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;

@Named
public class DeletingComment implements JavaDelegate {

    private final CommentRepository commentRepository;

    public DeletingComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // find comment with max id where isActive null

        commentRepository.deleteAllByIsActive(null);
    }
}
