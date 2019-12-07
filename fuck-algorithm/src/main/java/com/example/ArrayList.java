package com.example;


/**
 * 动态数组
 */
public class ArrayList {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add();
        list.add();
    }


    private int size;
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = (capacity <= DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index = " + index + ",size=" + size);
        }
        return elements[index];
    }

    public int set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index = " + index + ",size=" + size);
        }
        int oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean constains(int element) {
        return indexOf(element) != -1;
    }

    public void clear() {
        size = 0;
    }

    public void add(int element){

    }

    public int add(int index,int element){

    }
}
