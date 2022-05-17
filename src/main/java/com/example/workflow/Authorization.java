package com.example.workflow;

import com.example.workflow.domain.User;
import com.example.workflow.domain.UserRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class Authorization implements JavaDelegate {
    private final UserRepository userRepository;

    public Authorization(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String login = "";
        String password = "";
        String userId = "";


        login = (String) delegateExecution.getVariable("email");
        password = (String) delegateExecution.getVariable("password");

        User user = userRepository.getUserByLogin(login);

        if (user != null) {
            //if password would be hashed change here too
            if (user.getPassword().equals(password)) {
                userId = user.getId().toString();
            } else {
                userId = "-1";
                throw new BpmnError("authorizationFailed", "Wrong passwords");
            }
        } else {
            userId = "-1";
            throw new BpmnError("authorizationFailed", "User with this login not found");
        }

        delegateExecution.setVariable("userId", userId);
    }
}
