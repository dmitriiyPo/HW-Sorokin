package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.example.spring_miniBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShowAllUsersCommand implements OperationCommand {

    private final UserService userService;

    @Autowired
    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void execute() {
        System.out.println("\nList of all users:");
        userService.showAllUsers().forEach(System.out::println);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }

}
