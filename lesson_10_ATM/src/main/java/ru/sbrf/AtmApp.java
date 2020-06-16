package ru.sbrf;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AtmApp {
    public static void main(String[] args) throws IOException, ParseException {
        ATM atm = new ATM();
        atm.run(args[0]);
    }
}
