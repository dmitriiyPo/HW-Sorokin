package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.console.ConsoleInput;
import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.example.spring_miniBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawAccountCommand implements OperationCommand {

    private final AccountService accountService;
    private final ConsoleInput consoleInput;

    @Autowired
    public WithdrawAccountCommand(AccountService accountService, ConsoleInput consoleInput) {
        this.accountService = accountService;
        this.consoleInput = consoleInput;
    }

    @Override
    public void execute() {
        int accountId = consoleInput.readPositiveInt("Enter accountId:", "account id");
        int amount = consoleInput.readPositiveInt("Enter amount:", "amount");
        accountService.withdrawAccount(accountId, amount);
        System.out.println("Withdrawn " + amount + " from accountId " + accountId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}
