package ru.sbrf;

import lombok.Data;

@Data
public class Storage {

    private int bill100, bill500, bill1000;

    protected Storage(int bill100, int bill500, int bill1000) {
        this.bill100 = bill100;
        this.bill500 = bill500;
        this.bill1000 = bill1000;
    }

    public int getTotal() {
        return bill1000 + bill500 + bill100;
    }
}