package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.console.ConsoleInput;
import org.example.spring_miniBank.model.Account;
import org.example.spring_miniBank.model.User;
import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.example.spring_miniBank.service.AccountService;
import org.example.spring_miniBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountCommand implements OperationCommand {

    private final UserService userService;
    private final AccountService accountService;
    private final ConsoleInput consoleInput;

    @Autowired
    public CreateAccountCommand(UserService userService, AccountService accountService, ConsoleInput consoleInput) {
        this.userService = userService;
        this.accountService = accountService;
        this.consoleInput = consoleInput;
    }

    @Override
    public void execute() {
        int userId = consoleInput.readPositiveInt("Enter user id:", "user id");
        User user = userService.findUserById(userId);
        Account account = accountService.createAccount(user);
        user.getAccountList().add(account);
        System.out.println("Account created: " + account);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}
