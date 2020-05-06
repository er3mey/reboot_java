package ru.sbrf;

public class Storage extends Money {

    protected Storage(int bill100, int bill500, int bill1000) {
        super(bill100, bill500, bill1000);
    }

    public int getTotal() {
        return getBill500() * 500 + getBill100() * 100 + getBill1000() * 1000;
    }

    public void storageCapacity() {
        System.out.printf("Текущее кол-во купюр в банкомате:%n" +
                "[100]: " + getBill100() + "%n" +
                "[500]: " + getBill500() + "%n" +
                "[1000]: " + getBill1000() + "%n");
    }
}
