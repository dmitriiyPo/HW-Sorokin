package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.console.ConsoleInput;
import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.example.spring_miniBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositAccountCommand implements OperationCommand {

    private final AccountService accountService;
    private final ConsoleInput consoleInput;

    @Autowired
    public DepositAccountCommand(AccountService accountService, ConsoleInput consoleInput) {
        this.accountService = accountService;
        this.consoleInput = consoleInput;
    }

    @Override
    public void execute() {
        int accountId = consoleInput.readPositiveInt("Enter accountId:", "account id");
        int amount = consoleInput.readPositiveInt("Enter amount:", "amount");
        accountService.depositAccount(accountId, amount);
        System.out.println("Deposited: " + amount + " to accountId " + accountId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}
