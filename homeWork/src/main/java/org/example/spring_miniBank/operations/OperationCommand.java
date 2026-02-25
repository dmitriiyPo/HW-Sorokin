package org.example.spring_miniBank.operations;

public interface OperationCommand {
    void execute();
    ConsoleOperationType getOperationType();
}
