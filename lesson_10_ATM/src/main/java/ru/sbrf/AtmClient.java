package ru.sbrf;

public interface AtmClient {
    boolean withdraw(Storage storage, int value);

    boolean deposit(Storage storage, String value);
}
