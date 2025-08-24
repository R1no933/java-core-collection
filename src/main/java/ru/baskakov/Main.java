package ru.baskakov;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
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
                    case 1 -> manager.add(scanner);
                    case 2 -> manager.deleteContact(scanner);
                    case 3 -> manager.showContacts();
                    case 4 -> manager.searchContactByNumber(scanner);
                    case 5 -> manager.showContactsByGroup(scanner);
                    case 0 -> quit = true;
                    default -> System.out.println(INCORRECT_NUMBER);
                }
            } catch (InputMismatchException e) {
                System.out.println(INCORRECT_NUMBER);
            }
        }
    }
}