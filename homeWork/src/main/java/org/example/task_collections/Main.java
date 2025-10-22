package org.example.task_collections;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ContactManager cm = new ContactManager();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        while (!end) {

            showMenu();

            int option = readInt(scanner);


            if (option < 0 || option > 5) {
                System.out.println("\nДействие под таким номером не существует" +
                        "\nВыберите дейстиве от 0 до 5");
            }

            switch (option) {
                case 1 -> addContact(scanner, cm);
                case 2 -> removeContact(scanner, cm);
                case 3 -> printContacts(cm);
                case 4 -> searchContactByName(scanner, cm);
                case 5 -> showContactByGroup(scanner, cm);
                case 0 -> end = true;
            }

        }
    }


    public static void showMenu() {
        System.out.println("\nВыберите действие: " +
                "\n1 - Добавить контакт" +
                "\n2 - Удалить контакт" +
                "\n3 - Посмотреть все контакты" +
                "\n4 - Найти контакт по имени" +
                "\n5 - Посмотреть контакты по группе" +
                "\n0 - Выход");
    }


    public static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\nНекорректный ввод. Введите число.");
            }
        }
    }


    public static void addContact(Scanner scanner, ContactManager cm) {

        try {
            System.out.print("\nВведите имя: ");
            String name = scanner.nextLine();

            System.out.print("Введите телефон: ");
            String number = scanner.nextLine();

            System.out.print("Введите email: ");
            String email = scanner.nextLine();

            System.out.print("Введите группу: ");
            String group = scanner.nextLine();

            cm.addContact(name, number, email, group);
        } catch (InputMismatchException e) {
            System.out.println("\nНекорректный ввод.");
        }

    }


    public static void removeContact(Scanner scanner, ContactManager cm) {
        try {
            System.out.print("\nВведите имя контакта: ");
            String name = scanner.nextLine();
            cm.removeContact(name);
        } catch (InputMismatchException e) {
            System.out.println("\nНекорректный ввод.");
        }
    }


    public static void printContacts(ContactManager cm) {
        cm.printContacts();
    }


    public static void searchContactByName(Scanner scanner, ContactManager cm) {
        try {
            System.out.print("\nВведите имя: ");
            String name = scanner.nextLine();
            cm.searchContacts(name);
        } catch (InputMismatchException e) {
            System.out.println("\nНекорректный ввод.");
        }
    }


    public static void showContactByGroup(Scanner scanner, ContactManager cm) {
        try {
            System.out.print("\nВведите название группы: ");
            String group = scanner.nextLine();
            cm.showContactsByGruop(group);
        } catch (InputMismatchException e) {
            System.out.println("\nНекорректный ввод.");
        }
    }

}
