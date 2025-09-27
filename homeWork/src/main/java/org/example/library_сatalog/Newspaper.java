package org.example.library_сatalog;

import java.util.Objects;

public class Newspaper extends Publication {

    private String publicationDay;

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        this.publicationDay = publicationDay;
    }

    @Override
    public String getType() {
        return "Газета: ";
    }

    @Override
    public String printDetails() {
        return String.format(
                "Название - %s, Автор - %s, Год - %d, День публикации - %s",
                getTitle(),
                getAuthor(),
                getYear(),
                getPublicationDay());
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }


    @Override
    public String toString() {
        return "Newspaper {" + super.toString() + "publicationDay='" + publicationDay + "'} ";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Newspaper newspaper = (Newspaper) obj;
        return Objects.equals(publicationDay, newspaper.publicationDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationDay);
    }
}
