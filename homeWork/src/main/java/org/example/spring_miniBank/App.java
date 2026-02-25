package org.example.spring_miniBank;

import org.example.spring_miniBank.config.AppConfig;
import org.example.spring_miniBank.console.OperationsConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            OperationsConsoleListener operationsConsoleListener = context.getBean(OperationsConsoleListener.class);
            operationsConsoleListener.runBank();
        }

    }
}
