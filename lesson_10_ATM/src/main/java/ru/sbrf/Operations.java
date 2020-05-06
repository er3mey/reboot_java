package ru.sbrf;

public class Operations implements AtmClient, AtmCollector {

    @Override
    public boolean deposit(Storage storage, int value) {

        if (!isValueValid(value)) {
            System.err.println("Неверная сумма для внесения!");
            return false;
        }

        storage.setBill1000(storage.getBill1000() + value / Bill.is1000.val());
        value -= value / Bill.is1000.val() * Bill.is1000.val();

        storage.setBill500(storage.getBill500() + value / Bill.is500.val());
        value -= value / Bill.is500.val() * Bill.is500.val();

        storage.setBill100(storage.getBill100() + value / Bill.is100.val());

        System.out.println("Деньги успешно добавлены на ваш счёт.");
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
