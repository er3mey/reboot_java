package ru.sbrf;

public class Account extends Money {

//    private int bill100, bill500, bill1000, total;

    public Account(int bill100, int bill500, int bill1000) {
        super(bill100, bill500, bill1000);
    }

    public int getTotal() {
        return getBill500() * 500 + getBill100() * 100 + getBill1000() * 1000;
    }
}
