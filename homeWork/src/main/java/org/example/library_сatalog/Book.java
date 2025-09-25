package org.example.library_сatalog;

import java.util.Objects;

public class Book extends Publication {

    private String ISBN;

    public Book(String title, String author, int year, String ISBN) {
        super(title, author, year);
        this.ISBN = ISBN;
    }

    @Override
    public String getType() {
        return "Книга: ";
    }

    @Override
    public String printDetails() {
        return String.format(
                "Название - %s, Автор - %s, Год - %d, ISBN - %s",
                getTitle(),
                getAuthor(),
                getYear(),
                getISBN());
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book {" + super.toString() + "ISBN='" + ISBN + "'} ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Book book = (Book) obj;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ISBN);
    }
}
