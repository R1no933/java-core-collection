package ru.baskakov;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String SPLITTER = "==========================================================================";
    private static final String MAIN_MENU = """
            ==========================================================================
            1. Добавить контакт.
            2. Удалить контакт.
            3. Посмотреть все контакты.
            4. Найти контакт.
            5. Посмотреть контакты по группе.
            0. Выход.
            ==========================================================================
            """;
    private static final String INCORRECT_NUMBER = "Введите только цифру пункта меню!";

    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int choice;
        while (!quit) {
            System.out.println(MAIN_MENU);
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.println(SPLITTER);
                        System.out.println("Введите имя контакта:");
                        String name = scanner.nextLine();
                        System.out.println("Введите номер контакта:");
                        String phone = scanner.nextLine();
                        System.out.println("Введите адрес электронной почты контакта:");
                        String email = scanner.nextLine();
                        System.out.println("Введите группу контакта:");
                        String group = scanner.nextLine();
                        Contact contact = new Contact(name, phone, email, group);
                        manager.add(contact);
                        System.out.println(SPLITTER);
                    }
                    case 2 -> {
                        System.out.println(SPLITTER);
                        System.out.println("Введите номер телефона для удаления контакта:");
                        String phone = scanner.nextLine();
                        manager.deleteContact(phone);
                        System.out.println(SPLITTER);
                    }
                    case 3 -> {
                        System.out.println(SPLITTER);
                        manager.showContacts();
                        System.out.println(SPLITTER);
                    }
                    case 4 -> {
                        {
                            System.out.println(SPLITTER);
                            System.out.println("Введите номер телефона, по которому искать контакт:");
                            String phone = scanner.nextLine();
                            manager.searchContactByNumber(phone);
                            System.out.println(SPLITTER);
                        }
                    }
                    case 5 -> {
                        System.out.println(SPLITTER);
                        System.out.println("Введите название группы:");
                        String group = scanner.nextLine();
                        manager.showContactsByGroup(group);
                        System.out.println(SPLITTER);
                    }
                    case 0 -> quit = true;
                    default -> System.out.println(INCORRECT_NUMBER);
                }
            } catch (InputMismatchException e) {
                System.out.println(INCORRECT_NUMBER);
            }
        }
    }
}