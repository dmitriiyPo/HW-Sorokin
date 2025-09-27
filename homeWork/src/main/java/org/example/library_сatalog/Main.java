package org.example.library_сatalog;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        do {
            System.out.println("\nВыберете действие: " +
                    "\n 1. Добавить новую публикацию" +
                    "\n 2. Вывести список всех публикаций" +
                    "\n 3. Поиск публикации по автору" +
                    "\n 4. Вывести общее количество публикаций" +
                    "\n 5. Удаление публикации" +
                    "\n 0. Выход\t");

            int numberMenu = scanner.nextInt();
            scanner.nextLine();

            switch (numberMenu) {
                case 1:
                    System.out.println("\nВыберете тип публикации: " +
                            "\n 1. Книга" +
                            "\n 2. Журнал" +
                            "\n 3. Газета");


                    int numberAdd = scanner.nextInt();
                    scanner.nextLine();

                    if (numberAdd >= 1 && numberAdd <= 3) {
                        print(library, scanner, numberAdd);
                    }
                    break;

                case 2:
                    library.listPublications();
                    break;

                case 3:
                    System.out.print("\nВведите автора: ");
                    String findByAuthor = scanner.nextLine();
                    library.searchByAuthor(findByAuthor);
                    break;

                case 4:
                    System.out.print("\nОбщее кол-во публикаций: ");
                    System.out.println(Publication.getPublicationCount());
                    break;

                case 5:
                    System.out.print("\nВведите название публикации: ");
                    String titleDelete = scanner.nextLine();
                    library.deletedPublication(titleDelete);
                    break;

                case 0:
                    end = true;
                    break;
            }
        } while (!end) ;
        scanner.close();
    }

    public static void print(Library library,Scanner scanner, int numberAdd) {
        System.out.print("\nВведите название: ");
        String titleBook = scanner.nextLine();
        System.out.print("Введите автора: ");
        String authorBook = scanner.nextLine();
        System.out.print("Введите год: ");
        int yearBook = scanner.nextInt();
        scanner.nextLine();
        if (numberAdd == 1) {
            System.out.print("Введите ISBN: ");
            String isbnBook = scanner.nextLine();
            library.addPublication(new Book (titleBook, authorBook, yearBook, isbnBook));
        } else if (numberAdd == 2) {
            System.out.print("Введите номер выпуска: ");
            int issueNumberMagazine = scanner.nextInt();
            library.addPublication(new Magazine (titleBook, authorBook, yearBook, issueNumberMagazine));
        } else if (numberAdd == 3) {
            System.out.print("Введите день публикации: ");
            String publicationDay = scanner.nextLine();
            library.addPublication(new Newspaper (titleBook, authorBook, yearBook, publicationDay));
        }
    }

}
