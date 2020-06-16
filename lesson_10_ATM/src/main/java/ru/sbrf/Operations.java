package ru.sbrf;

import java.util.Scanner;

public class Operations implements AtmClient, AtmCollector {

    @Override
    public boolean deposit(Storage storage, String value) {

        Scanner input = new Scanner(System.in);
        String singleVal = input.nextLine();
        Scanner valueLine = new Scanner(singleVal);
        while (valueLine.hasNext()) {
            String val = valueLine.next("[0-9]*");
            switch (val) {
                case "100":
                    storage.setBill100(storage.getBill100() + 1);
                    break;
                case "500":
                    storage.setBill500(storage.getBill500() + 1);
                    break;
                case "1000":
                    storage.setBill1000(storage.getBill1000() + 1);
                    break;
                default:
                    System.err.format("Банкнота с номиналом %s не поддерживается, заберите\n", val);
                    break;
            }
        }
        System.out.println("\nДеньги успешно добавлены на ваш счёт.\n");
        return true;
    }

    @Override
    public boolean withdraw(Storage storage, int value) {

        if (!isValueValid(value)) {
            System.err.println("Неверная сумма для снятия!");
            return false;
        }

        int bill1000, bill500, bill100;
        bill1000 = value / Bill.is1000.val();
        value -= bill1000 * Bill.is1000.val();
        bill500 = value / Bill.is500.val();
        value -= bill500 * Bill.is500.val();
        bill100 = value / Bill.is100.val();
        ;

        if (storage.getBill1000() >= bill1000
                && storage.getBill500() >= bill500
                && storage.getBill100() >= bill100) {
            storage.setBill1000(storage.getBill1000() - bill1000);
            storage.setBill500(storage.getBill500() - bill500);
            storage.setBill100(storage.getBill100() - bill100);
        } else {
            System.err.println("Недостаточно купюр в банкомате!");
            return false;
        }

        System.out.println("Возьмите деньги, пожалуйста.");
        return true;
    }

    @Override
    public void getStorageCapacity(Storage storage) {
        storage.getTotal();
    }

    private boolean isValueValid(int value) {
        if (value % Bill.is1000.val() % Bill.is500.val() % Bill.is100.val() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEnoughMoney(Storage storage, int value) {
        if (value > storage.getTotal()) {
            return false;
        } else {
            return true;
        }
    }
}
