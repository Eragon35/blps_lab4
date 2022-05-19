package com.example.workflow.service;

import com.example.workflow.domain.User.User;
import com.example.workflow.domain.User.UserRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class Registration implements JavaDelegate {
    private final UserRepository userRepository;

    public Registration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String userId = "";
        String login = "";
        String password = "";
        String repeatPassword = "";

        login = (String) delegateExecution.getVariable("emailReg");
        password = (String) delegateExecution.getVariable("password");
        repeatPassword = (String) delegateExecution.getVariable("repeatPassword");

        if (password.equals(repeatPassword)) {
            //TODO: check if email not used
            //TODO: hash password before save to db
            User user = new User(login, password);
            userId = userRepository.save(user).getId().toString();
        } else {
            userId = "-1";
            delegateExecution.setVariable("regError", "Passwords are not same");
            throw new BpmnError("registrationFailed", "Passwords are not same");
        }

        delegateExecution.setVariable("userId", userId);
    }
}
