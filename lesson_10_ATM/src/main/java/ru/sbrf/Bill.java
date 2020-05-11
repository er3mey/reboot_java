package ru.sbrf;

public enum Bill {

    is1000(1000),
    is500(500),
    is100(100);

    private final int value;

    Bill(final int newValue) {
        value = newValue;
    }

    public int val() {
        return value;
    }
}