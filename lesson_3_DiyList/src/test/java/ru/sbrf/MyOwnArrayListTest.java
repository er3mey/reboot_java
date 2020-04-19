package ru.sbrf;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Unit test for MyOwnArrayList.
 */
public class MyOwnArrayListTest {

    MyOwnArrayList<String> listOfLetters;
    MyOwnArrayList<String> listOfSortedLetters;
    MyOwnArrayList<String> listOfNumbers;

    String[] dataLetters =
            {"г", "д", "е", "а", "в", "б", "ю", "э", "я", "ы", "ь", "ъ", "х", "щ", "ш", "ё", "ж", "и", "к", "л", "м", "о"};

    String[] sortedDataLetters =
            {"а", "б", "в", "г", "д", "е", "ж", "и", "к", "л", "м", "о", "х", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я", "ё"};

    String[] dataNumbers =
            {"21", "20", "19", "18", "17", "16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};

    public void fillList(MyOwnArrayList<String> myOwnArrayList, String[] data) {
        for (int i = 0; i < data.length; i++) {
            myOwnArrayList.add(data[i]);
        }
    }

    public void initLists() {
        listOfLetters = new MyOwnArrayList<>();
        fillList(listOfLetters, dataLetters);
        listOfSortedLetters = new MyOwnArrayList<>();
        fillList(listOfSortedLetters, sortedDataLetters);
        listOfNumbers = new MyOwnArrayList<>();
        fillList(listOfNumbers, dataNumbers);
    }

    @Test
    public void addAllTest() {
        initLists();
        Collection<String> collection = new HashSet<>();

        collection.addAll(listOfLetters);

        System.out.println("Результат addAll: " + collection);

        for (String letter : listOfLetters) {
            Assert.assertTrue("отсутвует символ - " + letter, collection.contains(letter));
        }
    }

    @Test
    public void copyTest() {
        initLists();
        Collections.copy(listOfNumbers, listOfLetters);

        System.out.print("Результат copy: ");
        listOfNumbers.printList();

        Assert.assertTrue("Списки не одинаковые после copy()!",
                Arrays.equals(listOfLetters.toArray(), listOfNumbers.toArray()));
    }

    @Test
    public void sortTest() {
        initLists();
        Collections.sort(listOfLetters);

        System.out.print("Результат sort: ");
        listOfLetters.printList();

        Assert.assertTrue("Список не отсортировался ожидаемо! -" + sortedDataLetters.toString(),
                Arrays.equals(listOfLetters.toArray(), listOfSortedLetters.toArray()));
    }
}
