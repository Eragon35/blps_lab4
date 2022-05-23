package com.example.workflow.service;

import com.example.workflow.domain.Comment.CommentRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;

@Named
public class SavingOrDeletingCommentAfterCheck implements JavaDelegate {

    private final CommentRepository commentRepository;

    public SavingOrDeletingCommentAfterCheck(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // check isActive key from form and update isActive field in comment

        Boolean isActive = (Boolean) delegateExecution.getVariable("isActive");
        Long commentId = Long.parseLong(delegateExecution.getVariable("commentId").toString());

        //TODO: add some checks for commentId & isActive
        commentRepository.updateIsActive(commentId, isActive);
    }
}
