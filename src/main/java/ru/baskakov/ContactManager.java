package ru.baskakov;

import java.util.*;

public class ContactManager {
    private static final String SPLITTER = "==========================================================================";
    private static final String NOT_FOUND = "Контакт не найден!";

    private ArrayList<Contact> contactsList = new ArrayList<>();
    private Set<Contact> contactsSet = new HashSet<>();
    private Map<String, ArrayList<Contact>> contactsMap = new HashMap<>();

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

    public void add(Contact contact) {
        boolean isPresent = contactsSet.contains(contact);
        if (!isPresent) {
            contactsList.add(contact);
            contactsSet.add(contact);
            contactsMap.put(contact.getGroup(), contactsList);
            System.out.println("Контакт добавлен.");
        } else {
            System.out.println("Такой контакт уже существует!");
        }

    }

    public void deleteContact(String phone) {
        boolean found = false;
        isEmptyArray(contactsList);

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
    }

    public void showContacts() {
        isEmptyArray(contactsList);
        Iterator<Contact> contactListIterator = contactsList.iterator();
        while (contactListIterator.hasNext()) {
            Contact contact = contactListIterator.next();
            System.out.println(contact.toString());
        }
    }

    public void searchContactByNumber(String phone) {
        boolean found = false;
        isEmptyArray(contactsList);

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
    }

    public void showContactsByGroup(String group) {
        isEmptyMap(contactsMap);

        if (!contactsMap.containsKey(group)) {
            System.out.println("Такой группы не существует");
        } else {
            System.out.println(String.format("---------- %s ----------", group));
            for (Contact contact : contactsMap.get(group)) {
                System.out.println(contact.toString());
            }
        }
    }
}
