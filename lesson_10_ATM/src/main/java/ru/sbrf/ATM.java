package ru.sbrf;

import java.util.Scanner;

public class ATM{

    private static Operations operations;
    private static Storage storage;
    private static Account account;
    private static Scanner input;
    private static int value;

    public static void main(String[] args) {
        operations = new Operations();
        storage = new Storage(2, 5, 5);
        account = new Account(4, 1, 1);
        input = new Scanner(System.in);

        while (true) {
            System.out.printf("%nНа вашем л/c: [" + operations.balance(account) + "]%n" +
                    "Выберите операцию:%n" +
                    "[1] - Внести деньги на счёт%n" +
                    "[2] - Вывести деньги со счёта%n%n" +
                    "[0] - Режим для инкассатора!%n%n");

            int operationType = input.nextInt();
            switch (operationType) {
                case 1:
                    System.out.println("Введите желаемую сумму для ввода");
                    value = input.nextInt();
                    operations.deposit(account, value, storage);
                    break;
                case 2:
                    System.out.println("Введите желаемую сумму для снятия");
                    value = input.nextInt();
                    operations.withdraw(account, value, storage);
                    break;
                case 0:
                    operations.getStorageCapacity(storage);
                    break;
                default:
                    System.err.println("Неверная команда, выберите корректную!");
            }
        }
    }



}
