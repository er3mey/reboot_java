package ru.sbrf;

import java.lang.reflect.Method;


public class Runner {
    public static void main(String[] args) {

        int passed = 0;
        int failed = 0;
        int count = 0;

        //Используем название класса-теста для запуска
        Class<TestExample> obj = TestExample.class;

        //Выполнение методов под аннотацией @Before
        for (Method beforeMethods : obj.getDeclaredMethods()) {

            if (beforeMethods.isAnnotationPresent(Before.class)) {
                try {
                    beforeMethods.invoke(obj.newInstance());
                    System.out.printf("[BEFORE] '%s' - DONE%n", beforeMethods.getName());
                } catch (Throwable ex) {
                    System.out.printf("[BEFORE] '%s' - FAILED: %s %n", beforeMethods.getName(), ex.getCause());
                }
            }
        }

        //Запуск тестов с аннотацией @Test
        for (Method testMethods : obj.getDeclaredMethods()) {
            if (testMethods.isAnnotationPresent(Test.class)) {
                try {
                    testMethods.invoke(obj.newInstance());
                    System.out.printf("[%s] '%s' - PASSED %n", ++count, testMethods.getName());
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("[%s] '%s' - FAILED: %s %n", ++count, testMethods.getName(), ex.getCause());
                    failed++;
                }
            }
        }

        //@AFTER
        for (Method afterMethods : obj.getDeclaredMethods()) {
            if (afterMethods.isAnnotationPresent(After.class)) {
                try {
                    afterMethods.invoke(obj.newInstance());
                    System.out.printf("[AFTER] '%s' - DONE", afterMethods.getName());
                } catch (Throwable ex) {
                    System.out.printf("[AFTER] '%s' - FAILED: %s %n", afterMethods.getName(), ex.getCause());
                }

            }
        }
        System.out.printf("%n=================" +
                "%nTOTAL TESTS: [%d] %n(\u2705) PASSED: [%d] %n(\u274C) FAILED: [%d] %n" +
                "=================%n", count, passed, failed);
    }
}


