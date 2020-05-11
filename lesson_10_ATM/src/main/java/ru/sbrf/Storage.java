package ru.sbrf;

public class Storage {

    private int bill100, bill500, bill1000;

    protected Storage(int bill100, int bill500, int bill1000) {
        this.bill100 = bill100;
        this.bill500 = bill500;
        this.bill1000 = bill1000;
    }

    public int getBill100() {
        return bill100;
    }

    public void setBill100(int bill100) {
        this.bill100 = bill100;
    }

    public int getBill500() {
        return bill500;
    }

    public void setBill500(int bill500) {
        this.bill500 = bill500;
    }

    public int getBill1000() {
        return bill1000;
    }

    public void setBill1000(int bill1000) {
        this.bill1000 = bill1000;
    }

    public int getTotal() {
        return bill1000 + bill500 + bill100;
    }
}