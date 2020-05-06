package ru.sbrf;

public interface AtmClient {
    boolean withdraw(Account account, int value, Storage storage);

    boolean deposit(Account account, int value, Storage storage);
}
