package com.example.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class SendingMessage implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("unchekedComments")
                .setVariable("comments", delegateExecution.getVariable("list"))
                .correlate();
    }
}
