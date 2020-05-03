package ru.sbrf;

public class TestExample {

    @Before
    public void beforeTests() {
        assert true : "Подготовились к тестам, можно начинать";
    }

    @After
    public void afterTests() {
        assert true : "Отдыхаем от всех этих тестов, выдыхаем...";
    }

    @Test
    public void test1() {
        assert true : "Пока всё ОК!";
    }

    @Test()
    public void test2() {
        throw new RuntimeException("Ошибка, что то пошло не так ;(");
    }

    @Test()
    public void test3() {
        assert true : "Всё ОК, работаем даже после ошибки :)";
    }

}
