package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.console.ConsoleInput;
import org.example.spring_miniBank.model.User;
import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.example.spring_miniBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand implements OperationCommand {

    private final UserService userService;
    private final ConsoleInput consoleInput;

    @Autowired
    public CreateUserCommand(UserService userService, ConsoleInput consoleInput) {
        this.userService = userService;
        this.consoleInput = consoleInput;
    }

    @Override
    public void execute() {
        String login = consoleInput.readRequiredString("Enter login:", "login");
        User user = userService.userCreate(login);
        System.out.println("User created: " + user);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
