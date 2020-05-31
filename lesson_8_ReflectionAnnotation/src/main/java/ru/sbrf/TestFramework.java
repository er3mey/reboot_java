package ru.sbrf;

import java.lang.reflect.Method;


public class TestFramework {
    public void runTest(Class testClass) {

        int passed = 0;
        int failed = 0;
        int count = 0;

        //Используем название класса-теста для запуска
//        Class<TestExample> obj = TestExample.class;
        Class<TestExample> obj = testClass;

        Method beforeMethod = null;
        Method afterMethod = null;

        //Заполнение методов под аннотацией @Before и @After
        for (Method allMethods : obj.getDeclaredMethods()) {

            if (allMethods.isAnnotationPresent(Before.class)) {
                beforeMethod = allMethods;
            }
            if (allMethods.isAnnotationPresent(After.class)) {
                afterMethod = allMethods;
            }
        }

        //Запуск тестов с аннотацией @Test
        for (Method testMethods : obj.getDeclaredMethods()) {
            if (testMethods.isAnnotationPresent(Test.class)) {
                try {
                    beforeMethod.invoke(obj.newInstance());
                    System.out.printf("[BEFORE] '%s' - DONE%n", beforeMethod.getName());
                } catch (Throwable ex) {
                    System.out.printf("[BEFORE] '%s' - FAILED: %s %n", beforeMethod.getName(), ex.getCause());
                }
                try {
                    testMethods.invoke(obj.newInstance());
                    System.out.printf("[%s] '%s' - PASSED %n", ++count, testMethods.getName());
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("[%s] '%s' - FAILED: %s %n", ++count, testMethods.getName(), ex.getCause());
                    failed++;
                }
                try {
                    afterMethod.invoke(obj.newInstance());
                    System.out.printf("[AFTER] '%s' - DONE %n", afterMethod.getName());
                } catch (Throwable ex) {
                    System.out.printf("[AFTER] '%s' - FAILED: %s %n", afterMethod.getName(), ex.getCause());
                }

            }
        }

        System.out.printf("%n=================" +
                "%nTOTAL TESTS: [%d] %n(\u2705) PASSED: [%d] %n(\u274C) FAILED: [%d] %n" +
                "=================%n", count, passed, failed);
    }
}


