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
public class CloseAccountCommand implements OperationCommand {

    private final AccountService accountService;
    private final UserService userService;
    private final ConsoleInput consoleInput;

    @Autowired
    public CloseAccountCommand(AccountService accountService, UserService userService, ConsoleInput consoleInput) {
        this.accountService = accountService;
        this.userService = userService;
        this.consoleInput = consoleInput;
    }

    @Override
    public void execute() {
        int accountId = consoleInput.readPositiveInt("Enter accountId", "account id");
        Account closedAccount = accountService.closeAccount(accountId);
        User user = userService.findUserById(closedAccount.getUserId());
        user.getAccountList().remove(closedAccount);
        System.out.println("Account " + accountId + " closed.");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
