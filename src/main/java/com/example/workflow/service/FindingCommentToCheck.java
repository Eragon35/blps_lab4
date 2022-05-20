package com.example.workflow.service;

import com.example.workflow.domain.Comment.Comment;
import com.example.workflow.domain.Comment.CommentRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class FindingCommentToCheck implements JavaDelegate {

    private final CommentRepository commentRepository;

    public FindingCommentToCheck(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // find comment with max id where isActive null

        Integer commentId = commentRepository.findLastUncheckedCommentId();

        if (commentId != null) {
            Comment comment = commentRepository.findCommentById(Long.valueOf(commentId));

            if (comment != null) {
                delegateExecution.setVariable("isActive", true);
                delegateExecution.setVariable("commentForCheck", comment.getComment());
                delegateExecution.setVariable("commentId", commentId);
            } else {
                delegateExecution.setVariable("isActive", false);
            }
        } else {
            delegateExecution.setVariable("isActive", false);
            delegateExecution.setVariable("exit", "Комментариев для проверки больше нет");
        }
    }
}
