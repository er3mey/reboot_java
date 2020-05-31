package ru.sbrf;

public class TestRunner {
    public static void main(String[] args) {
        TestFramework testFramework = new TestFramework();

        testFramework.runTest(TestExample.class);
    }
}
