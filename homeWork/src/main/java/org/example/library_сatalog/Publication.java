package org.example.library_сatalog;

import java.util.Objects;

public abstract class Publication implements Printable {

    private String title;
    private String author;
    private int year;
    private static int publicationCount = 0;

    public Publication(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public abstract String getType();

    @Override
    public abstract String printDetails();

    public static int getPublicationCount() {
        return publicationCount;
    }

    public static void setPublicationCount(int publicationCount) {
        Publication.publicationCount += publicationCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "'title='" + title + '\'' + ", author='" + author + '\'' + ", year=" + year + ", ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Publication publication = (Publication) obj;
        return year == publication.year && Objects.equals(title, publication.title) && Objects.equals(author, publication.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

}
