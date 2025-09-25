package org.example.library_сatalog;

import java.util.Objects;

public class Magazine extends Publication {

    private int issueNumber;

    public Magazine(String title, String author, int year, int issueNumber) {
        super(title, author, year);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getType() {
        return "Журнал: ";
    }

    @Override
    public String printDetails() {
        return String.format(
                "Название - %s, Автор - %s, Год - %d, Номер выпуска - %d",
                getTitle(),
                getAuthor(),
                getYear(),
                getIssueNumber());
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "Magazine {" + super.toString() + "issueNumber=" + issueNumber + "} ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Magazine magazine = (Magazine) obj;
        return issueNumber == magazine.issueNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), issueNumber);
    }
}
