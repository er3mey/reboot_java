package ru.sbrf;

import java.util.Scanner;

public class ATM {

    private static Operations operations;
    private static Storage storage;
    private static Scanner input;
    private static int value;

    public static void main(String[] args) {
        operations = new Operations();
        input = new Scanner(System.in);
        storage = new Storage(5, 5, 5);

        while (true) {
            System.out.printf(
                    "%nВыберите операцию:%n" +
                            "[1] - Внести деньги на счёт%n" +
                            "[2] - Вывести деньги со счёта%n%n" +
                            "[0] - Режим для инкассатора!%n%n");

            int operationType = input.nextInt();
            switch (operationType) {
                case 1:
                    System.out.println("Введите желаемую сумму для ввода");
                    value = input.nextInt();
                    operations.deposit(storage, value);
                    break;
                case 2:
                    System.out.println("Введите желаемую сумму для снятия");
                    value = input.nextInt();
                    operations.withdraw(storage, value);
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
