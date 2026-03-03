package org.example.spring_miniBank.console;

import org.example.spring_miniBank.operations.ConsoleOperationType;
import org.example.spring_miniBank.operations.OperationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OperationsConsoleListener {

    private final Map<ConsoleOperationType, OperationCommand> commandsMap;
    private final ConsoleInput consoleInput;
    private boolean running;

    @Autowired
    public OperationsConsoleListener(List<OperationCommand> commandsList, ConsoleInput consoleInput) {
        this.commandsMap = commandsList.stream()
                .collect(Collectors.toMap(
                        OperationCommand::getOperationType,
                        oc -> oc
                ));
        this.consoleInput = consoleInput;
        this.running = true;
    }


    public void runBank() {
        init();
        process();
    }


    private void process() {
        while (running) {
            ConsoleOperationType nextOperation = consoleInput.readOperationType();
            processNextCommand(nextOperation);
            if (nextOperation == ConsoleOperationType.EXIT) {
                running = false;
            }
        }
    }


    private void processNextCommand(ConsoleOperationType operationType) {
        try {
            OperationCommand command = commandsMap.get(operationType);
            if (command == null) {
                throw new IllegalStateException("No command handler for " + operationType);
            }
            command.execute();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
            System.out.println("Error: " + message);
        }
    }


    private void init() {
        System.out.println("MiniBank started. Type EXIT to stop.");
        consoleInput.printAvailableCommands();
    }

}
