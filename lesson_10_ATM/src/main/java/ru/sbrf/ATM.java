package ru.sbrf;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Написать эмулятор АТМ (банкомата).
 * <p>
 * Объект класса АТМ должен уметь:
 * <p>
 * // * Принимать банкноты разных номиналов
 * // * (на каждый номинал должна быть своя ячейка).
 * // * Выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать.
 * // * Это задание не на алгоритмы, а на проектирование. Поэтому оптимизировать выдачу не надо.
 * // * Выдавать сумму остатка денежных средств.
 * <p>
 * // Научить банкомат записывать своё состояние в файл, при запуске - загружать из файла.
 * <p>
 * // Формат - любой (xml/json/csv/protobuf)
 * //Не обязательно при запуске банкомата файл уже есть, должен уметь загружаться и без файла в наличии
 * //Файл передаем параметром в строке запуска
 * // Умеет читать с консоли
 * //Принять купюры (номинал в строку через пробел)
 * // Выдать купюры на запрошенную сумму
 * // Завершить работу (exit)
 * // * Логгируем свою деятельность
 */


public class ATM {

    private static Operations operations;
    private static Storage storage;
    private static Scanner input;
    private static int value;
    private static String valueInString;
    private static boolean untilExit = true;

    public void run(String fileNameArg) throws IOException, ParseException {
        JsonHelper jsonHelper = new JsonHelper();
        Logger logger = Logger.getLogger(ATM.class);
//        BasicConfigurator.configure();
        operations = new Operations();
        input = new Scanner(System.in);

        Storage storage = new Storage(0, 0, 0);
        File file = new File(fileNameArg);

        if (file.exists()) {
            jsonHelper.readFromJson(fileNameArg, storage);
        } else {
            jsonHelper.createNewJson(fileNameArg, storage);
        }

        logger.info("Запуск АТМ");
        while (untilExit) {
            System.out.printf(
                    "%nВыберите операцию:%n" +
                            "[1] - Внести деньги на счёт%n" +
                            "[2] - Вывести деньги со счёта%n%n" +
                            "[5] - Режим для инкассатора!%n%n" +
                            "[0] - Завершить обслуживание%n%n");

            int operationType = input.nextInt();
            switch (operationType) {
                case 1:
                    logger.info("Пользователь выбрал функцию введения денег на счёт");
                    System.out.println("Введите желаемую сумму для ввода номиналом банкнот через пробел(100 100 500):");
                    operations.deposit(storage, valueInString);
                    logger.info("Пользователь успешно добавил деньги на свой счёт");
                    break;
                case 2:
                    logger.info("Пользователь выбрал функцию вывода денег со своего счёта");
                    System.out.println("Введите желаемую сумму для снятия:");
                    value = input.nextInt();
                    operations.withdraw(storage, value);
                    logger.info("Пользователь успешно снял деньги со своего счёта");
                    break;
                case 5:
                    logger.info("Запрошена информация по остаткам средств в кассетах банокомата");
                    operations.getStorageCapacity(storage);
                    logger.info("Информация по остаткам средств в кассетах банокомата выдана");
                    break;
                case 0:
                    System.out.println("Завершаем обслуживание. До свидания!");
                    untilExit = false;
                    break;
                default:
                    logger.info("Введена неверная команда - " + operationType);
                    System.err.println("Неверная команда, выберите корректную!");
            }
            logger.info("АТМ завершает свою работу");

        }
        jsonHelper.writeToJson(fileNameArg, storage);
    }

}
