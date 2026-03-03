package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.console.ConsoleInput;
import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.example.spring_miniBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferAccountCommand implements OperationCommand {

    private final AccountService accountService;
    private final ConsoleInput consoleInput;

    @Autowired
    public TransferAccountCommand(AccountService accountService, ConsoleInput consoleInput) {
        this.accountService = accountService;
        this.consoleInput = consoleInput;
    }


    @Override
    public void execute() {
        int fromAccountId = consoleInput.readPositiveInt("Enter fromAccountId:", "fromAccount id");
        int toAccountId = consoleInput.readPositiveInt("Enter toAccountId:", "toAccount id");
        if (fromAccountId == toAccountId) {
            throw new IllegalArgumentException("source and target account id must be different");
        }
        int amount = consoleInput.readPositiveInt("Enter amount:", "amount");
        int commission = accountService.transferAccount(fromAccountId, toAccountId, amount);
        System.out.println("Transfer completed from account " + fromAccountId + " to account " + toAccountId + "."
        + " Amount: " + amount + ", commission: " + commission + ", получатель получил: " + (amount - commission));
    }


    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
