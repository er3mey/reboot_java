package ru.sbrf;

public class Operations implements AtmClient, AtmCollector {

    @Override
    public boolean deposit(Account account, int value, Storage storage) {

        if (!isValueValid(value)) {
            System.err.println("Неверная сумма для внесения!");
            return false;
        }

        int bill1000, bill500, bill100;
        bill1000 = value / 1000;
        value -= bill1000 * 1000;
        bill500 = value / 500;
        value -= bill500 * 500;
        bill100 = value / 100;

        account.setBill1000(account.getBill1000() + bill1000);
        storage.setBill1000(storage.getBill1000() + bill1000);

        account.setBill500(account.getBill500() + bill500);
        storage.setBill500(storage.getBill500() + bill500);

        account.setBill100(account.getBill100() + bill100);
        storage.setBill100(storage.getBill100() + bill100);

        System.out.println("Деньги успешно добавлены на ваш счёт. Текущий баланс: [" + balance(account) + "]");
        return true;
    }

    @Override
    public boolean withdraw(Account account, int value, Storage storage) {

        if (!isValueValid(value)) {
            System.err.println("Неверная сумма для снятия!");
            return false;
        } else if (!isEnoughMoney(account, value, storage)) {
            System.err.println("У вас недостаточно денег на счету!");
            return false;
        }

        int bill1000, bill500, bill100;
        bill1000 = value / 1000;
        value -= bill1000 * 1000;
        bill500 = value / 500;
        value -= bill500 * 500;
        bill100 = value / 100;

        if (storage.getBill1000() >= bill1000 && storage.getBill500() >= bill500 && storage.getBill100() >= bill100) {
            account.setBill1000(account.getBill1000() - bill1000);
            storage.setBill1000(storage.getBill1000() - bill1000);

            account.setBill500(account.getBill500() - bill500);
            storage.setBill500(storage.getBill500() - bill500);

            account.setBill100(account.getBill100() - bill100);
            storage.setBill100(storage.getBill100() - bill100);
        } else {
            System.err.println("Недостаточно купюр в банкомате!");
            return false;
        }

        System.out.println("Возьмите деньги, пожалуйста. Ваш остаток на счету: [" + balance(account) + "]");
        return true;
    }

    @Override
    public void getStorageCapacity(Storage storage) {
        storage.storageCapacity();
    }

    public int storageTotal(Storage storage) {
        return storage.getTotal();
    }

    public int balance(Account account) {
        return account.getTotal();
    }

    private boolean isValueValid(int value) {
        if (value % 1000 % 500 % 100 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEnoughMoney(Account account, int value, Storage storage) {
        if (value > balance(account) && value > storageTotal(storage)) {
            return false;
        } else {
            return true;
        }
    }
}
