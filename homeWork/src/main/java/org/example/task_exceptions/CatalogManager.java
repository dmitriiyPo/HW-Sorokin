package org.example.task_exceptions;

import java.util.ArrayList;
import java.util.List;

public class CatalogManager {

    private final List<Book> catalog;


    public CatalogManager() {
        catalog = new ArrayList<>();
    }

    public void addBook(String title, String author, int copies) {
        catalog.add(new Book(title, author, copies));
        System.out.println("\nКнига добавлена.");
    }

    public void takeBook(String title) throws NoCopyBookException, BookTitleNotFoundException {

        boolean take = false;

        if (!"".equals(title)) {
            for (Book book : catalog) {
                if (book.getTitle().equals(title)) {

                    if (book.getAvailableCopies() < 1) {
                        throw new NoCopyBookException("Нет копии книги в наличии");
                    }
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    System.out.println("\nКнига " + "[" + title + "]" + " успешно взята.");
                    take = true;
                }
            }
        } else {
            System.out.println("\nНазвание не введено.");
            return;
        }

        if (!take) {
            throw new BookTitleNotFoundException("Книга с таким названием " + "[" + title + "]" + " не найдена");
        }

    }

    public void returnBook(String title) throws BookTitleNotFoundException {

        boolean breturn = false;

        if (!"".equals(title)) {
            for (Book book : catalog) {
                if (book.getTitle().equals(title)) {
                    book.setAvailableCopies(book.getAvailableCopies() + 1);
                    System.out.println("\nКнига " + "[" + title + "]" + " успешно возвращена.");
                    breturn = true;
                }
            }
        } else {
            System.out.println("\nНазвание не введено.");
            return;
        }

        if (!breturn) {
            throw new BookTitleNotFoundException("Книга с таким названием " + "[" + title + "]" + " не найдена");
        }

    }

    public List<Book> getAllBooks() {
        return catalog;
    }
}
