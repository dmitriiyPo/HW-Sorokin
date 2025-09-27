package org.example.library_сatalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {

    private List<Publication> publications;

    public Library() {
        publications = new ArrayList<>();
    }

    public void addPublication(Publication publication) {
        if (!(publication == null)) {
            publications.add(publication);
            Publication.setPublicationCount(1);
            System.out.println("\nПубликация успешно дабавлена");
        } else {
            System.out.println("\nОбъект publication не задан");
        }
    }

    public void listPublications() {
        for(Publication publication : publications) {
            System.out.println(publication.toString());
        }
    }


    public void searchByAuthor(String author) {
        boolean found = false;
        if (!"".equals(author)) {
            for (Publication publication : publications) {
                if(publication.getAuthor().equals(author)) {
                    System.out.println(publication.getType() + publication.printDetails());
                    found = true;
                }
            }
        } else {
            System.out.println("\nАвтор не задан");
            return;
        }

        if (!found) {
            System.out.println("\nДанный автор [" + author + "]" + " не найден");
        }
    }

    public void deletedPublication(String title) {

        if ("".equals(title)) {
            System.out.println("\nНазвание публикации не задано");
            return;
        }

        Iterator<Publication> iterator = publications.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Publication publication = iterator.next();
            if (publication.getTitle().equals(title)) {
                iterator.remove();
                Publication.setPublicationCount(-1);
                System.out.println("\nПубликация с названием " + "[" + publication.getTitle() + "]" + " удалена");
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nПубликации с названием " + "[" + title + "]" + " не найдена");
        }

    }

}
