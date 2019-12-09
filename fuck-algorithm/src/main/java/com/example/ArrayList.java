package com.example;


import java.util.Arrays;

/**
 * 动态数组
 */
public class ArrayList {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list);

//        int old = list.remove(5);
//        System.out.println(old);

        list.add(0, 22);
        System.out.println(list);
    }


    private int size;
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 2;

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

    public int remove(int index) {
        int old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
//        System.arraycopy(elements,index+1,elements,index,size-index);
        size--;
        return old;
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

    public void add(int element) {
        add(size, element);
    }

    public void add(int index, int element) {
        if (index < 0 || (index > size && index<elements.length)) {
            throw new IndexOutOfBoundsException("index = " + index + ",size=" + size);
        }

        ensureCapacity(size+1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
//        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        int[] newElements = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("oldCapacity=" + oldCapacity + ",newCapacity" + newCapacity);
    }

    private void rangeCheck(int index) {
    }

    private void rangeCheckForAdd(int index) {
    }
}
