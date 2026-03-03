package org.example.spring_miniBank.operations.command;

import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements OperationCommand {


    @Override
    public void execute() {
        System.out.println("MiniBank stopped.");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.EXIT;
    }
}
