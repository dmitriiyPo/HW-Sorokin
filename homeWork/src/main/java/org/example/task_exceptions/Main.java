package org.example.task_exceptions;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CatalogManager cm = new CatalogManager();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        while(!end) {

            showMenu();

            int option = readInt(scanner);

            switch (option) {
                case 1 -> addBook(scanner, cm);
                case 2 -> takeBook(scanner, cm);
                case 3 -> returnBook(scanner, cm);
                case 4 -> showBooks(cm);
                case 5 -> end = true;
            }

        }

    }

    public static void showMenu() {
        System.out.println("\nВыберите дейстиве:" + "\n1. Добавить книгу" + "\n2. Взять книгу" +
                "\n3. Вернуть книгу" + "\n4. Показать все книги" + "\n5. Выход");
    }

    public static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число.");
            }
        }
    }

    public static void addBook(Scanner scanner, CatalogManager cm) {
        try {
            System.out.print("\nВведите название книги: ");
            String title = scanner.nextLine();

            System.out.print("Ведите автора: ");
            String author = scanner.nextLine();

            System.out.print("Ведите кол-во копий: ");
            int availableCopies = readInt(scanner);
            cm.addBook(title, author, availableCopies);

        } catch (InputMismatchException e) {
            System.out.println("\nНекоректный ввод: " + e);
            scanner.nextLine();
        }
    }

    public static void takeBook(Scanner scanner, CatalogManager cm) {
        System.out.print("\nВведите название книги: ");
        String title = scanner.nextLine();

        try {
            cm.takeBook(title);
        } catch (NoCopyBookException e) {
            System.out.println("\nОшибка копий: " + e.getMessage());
        } catch (BookTitleNotFoundException e) {
            System.out.println("\nОшибка: " + e.getMessage());
        }
    }

    public static void returnBook(Scanner scanner, CatalogManager cm) {
        System.out.print("\nВведите название книги: ");
        String title = scanner.nextLine();

        try {
            cm.returnBook(title);
        } catch (BookTitleNotFoundException e) {
            System.out.println("\nОшибка: " + e.getMessage());
        }
    }

    public static void showBooks(CatalogManager cm) {
        List<Book> books = cm.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("\nСписок книг пуст.");
        } else {
            System.out.println("\nСписок всех книг:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

}
