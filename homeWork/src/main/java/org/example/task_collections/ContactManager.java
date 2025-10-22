package org.example.task_collections;

import java.util.*;

public class ContactManager {

    private final List<Contact> listContacts;
    private final Set<Contact> setContacts;
    private final Map<String, List<Contact>> mapContacts;

    public ContactManager() {
        listContacts = new ArrayList<>();
        setContacts = new HashSet<>();
        mapContacts = new HashMap<>();
    }


    public void addContact(String name, String phone, String email, String group) {

        if (!"".equals(name) && !"".equals(phone) && !"".equals(email) && !"".equals(group)) {

            Contact contact = new Contact(name, phone, email, group);

            if (!setContacts.contains(contact)) {
                listContacts.add(contact);
                setContacts.add(contact);

                mapContacts.computeIfAbsent(group, k -> new ArrayList<>()).add(contact);

                System.out.println("\nКонтакт добавлен.");
            } else {
                System.out.println("\nТакой контакт уже существует!");
            }
        } else {
            System.out.println("\nВы не ввели одно из значений.");
        }

    }


    public void removeContact(String name) {

        if (!"".equals(name)) {
            Iterator<Contact> iteratorList = listContacts.iterator();
            boolean found = false;

            while (iteratorList.hasNext()) {
                Contact next = iteratorList.next();
                if (next.getName().equalsIgnoreCase(name)) {
                    iteratorList.remove();
                    setContacts.remove(next);
                    List<Contact> listMap = mapContacts.get(next.getGroup());

                    if (listMap != null) {
                        listMap.remove(next);
                        if (listMap.isEmpty()) {
                            mapContacts.remove(next.getGroup());
                        }
                    }
                    found = true;
                    System.out.println("\nКонтакт успешно удалён.");
                }
            }
            if (!found) {
                System.out.println("\nКонтакт с таким именем не найден.");
            }
        } else {
            System.out.println("\nИмя не введенно.");
        }

    }


    public void printContacts() {

        if (listContacts.isEmpty()) {
            System.out.println("\nСписок контатков пуст.");
            return;
        }

        System.out.println("\nВсе контакты: ");
        Iterator<Contact> iterator = listContacts.iterator();
        while (iterator.hasNext()) {
            Contact next = iterator.next();
            System.out.println(next.getName() + " | " + next.getPhone() + " | " + next.getEmail() + " | " + next.getGroup());
        }
    }


    public void searchContacts(String name) {

        if (!"".equals(name)) {
            boolean found = false;

            Iterator<Contact> iterator = setContacts.iterator();
            while (iterator.hasNext()) {
                Contact next = iterator.next();
                if (next.getName().equals(name)) {
                    System.out.println("Номер телефона " + "[" + name + "] : " + next.getPhone());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("\nКонтакт с таким именем не найден.");
            }
        } else {
            System.out.println("\nИмя не введенно.");
        }

    }


    public void showContactsByGruop(String group) {

        if (!"".equals(group)) {
            List<Contact> groupContacts = mapContacts.get(group);

            if (groupContacts == null || groupContacts.isEmpty()) {
                System.out.println("\nВ группе '" + group + "' нет контактов.");
                return;
            }

            System.out.println("\n---Контакты в группе '" + group + "':---");

            Iterator<Contact> iterator = groupContacts.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("\nНазвание группы не введенно.");
        }

    }

}
