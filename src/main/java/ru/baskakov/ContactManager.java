package ru.baskakov;

import java.util.*;

public class ContactManager {
    private static final String SPLITTER = "==========================================================================";
    private static final String INCORRECT_INPUT = "Введен не верный формат данных! Все поля заполняются строками!";
    private static final String NOT_FOUND = "Контакт не найден!";

    private ArrayList<Contact> contactsList = new ArrayList<>();
    private Set<Contact> contactsSet = new HashSet<>();
    private Map<String, ArrayList<Contact>> contactsMap = new HashMap<>();
    private Contact currentContact;

    private void isEmptyArray(ArrayList<Contact> list) {
        if (list.isEmpty()) {
            System.out.println(SPLITTER);
            System.out.println("Список контактов пуст!");
            System.out.println(SPLITTER);
        }
    }

    private void isEmptyMap(Map<String, ArrayList<Contact>> map) {
        if (map.isEmpty()) {
            System.out.println(SPLITTER);
            System.out.println("Список контактов пуст!");
            System.out.println(SPLITTER);
        }
    }

    public void add(Scanner scanner) {
        try {
            System.out.println("Введите имя контакта:");
            String name = scanner.nextLine();
            System.out.println("Введите номер контакта:");
            String phone = scanner.nextLine();
            System.out.println("Введите адрес электронной почты контакта:");
            String email = scanner.nextLine();
            System.out.println("Введите группу контакта:");
            String group = scanner.nextLine();
            currentContact = new Contact(name, phone, email, group);
            boolean isPresent = contactsSet.contains(currentContact);
            if (!isPresent) {
                contactsList.add(currentContact);
                contactsSet.add(currentContact);
                contactsMap.put(currentContact.getGroup(), contactsList);
                System.out.println("Контакт добавлен.");
            } else {
                System.out.println("Такой контакт уже существует!");
            }

        } catch (InputMismatchException e) {
            System.out.println(INCORRECT_INPUT);
        }
    }

    public void deleteContact(Scanner scanner) {
        boolean found = false;
        isEmptyArray(contactsList);

        try {
            System.out.println("Введите номер телефона для удаления контакта:");
            String phone = scanner.nextLine();
            Iterator<Contact> contactListIterator = contactsList.iterator();
            Iterator<Contact> contactSetIterator = contactsSet.iterator();
            while (contactListIterator.hasNext()) {
                String phoneNumber = contactListIterator.next().getPhone();
                if (phoneNumber.equals(phone)) {
                    contactListIterator.remove();
                    found = true;
                }
            }

            while (contactSetIterator.hasNext()) {
                String phoneNumber = contactSetIterator.next().getPhone();
                if (phoneNumber.equals(phone)) {
                    contactSetIterator.remove();
                }
            }

            if (!found) {
                System.out.println(NOT_FOUND);
            }
        } catch (InputMismatchException e) {
            System.out.println(INCORRECT_INPUT);
        }
    }

    public void showContacts() {
        isEmptyArray(contactsList);

        System.out.println(SPLITTER);
        Iterator<Contact> contactListIterator = contactsList.iterator();
        while (contactListIterator.hasNext()) {
            Contact contact = contactListIterator.next();
            System.out.println(contact.toString());
        }
        System.out.println(SPLITTER);
    }

    public void searchContactByNumber(Scanner scanner) {
        boolean found = false;
        isEmptyArray(contactsList);

        try {
            System.out.println(SPLITTER);
            System.out.println("Введите номер телефона, по которому искать контакт:");
            String phone = scanner.nextLine();
            Iterator<Contact> contactListIterator = contactsList.iterator();
            while (contactListIterator.hasNext()) {
                Contact contact = contactListIterator.next();
                if (contact.getPhone().equals(phone)) {
                    System.out.println(contact.toString());
                    found = true;
                }
            }

            if (!found) {
                System.out.println(NOT_FOUND);
            }

            System.out.println(SPLITTER);
        } catch (InputMismatchException e) {
            System.out.println(INCORRECT_INPUT);
        }
    }

    public void showContactsByGroup(Scanner scanner) {
        isEmptyMap(contactsMap);

        System.out.println(SPLITTER);
        System.out.println("Введите название группы:");
        String group = scanner.nextLine();
        if (!contactsMap.containsKey(group)) {
            System.out.println("Такой группы не существует");
        } else {
            System.out.println(String.format("---------- %s ----------", group));
            for (Contact contact : contactsMap.get(group)) {
                System.out.println(contact.toString());
            }
        }
        System.out.println(SPLITTER);
    }
}
