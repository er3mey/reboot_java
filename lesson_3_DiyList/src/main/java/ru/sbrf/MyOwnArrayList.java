package ru.sbrf;


import java.util.*;

public class MyOwnArrayList<E> implements List<E> {

    private int size = 0;
    private static final int DEFAULT_CAPACITY = 1;
    transient Object[] elementData = {};

    public MyOwnArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        int newSize = elementData.length + 1;
        elementData = Arrays.copyOf(elementData, newSize);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public void printList() {
        System.out.print("[");
        for (int i = 0; i < elementData.length; i++){
            System.out.print(elementData[i]);
            if (i != elementData.length-1) System.out.print(", ");
        }
        System.out.println("]");
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        } else
            return true;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int index = 0;

            public boolean hasNext() {
                if (index >= size) return false;
                return true;
            }

            public E next() {
                return (E) elementData[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        elementData = new Object[]{};
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            outOfBoundsMsg(index);
        }
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            outOfBoundsMsg(index);
        }
        E removedElement = (E) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            int listIndex = 0;

            @Override
            public boolean hasNext() {
                if (listIndex == size) return false;
                return true;
            }

            @Override
            public E next() {
                return (E) elementData[listIndex++];
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public E previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                Objects.checkIndex(listIndex - 1, size);
                elementData[listIndex - 1] = e;
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
