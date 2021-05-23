package org.example;

import java.util.concurrent.atomic.AtomicReference;

public class CasStack<E> {

    public static void main(String[] args) {
        CasStack<String> stack = new CasStack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
//        stack.print();

        stack.pop();
        stack.pop();
        stack.print();
    }

    private AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        // 将item封装Node，将成为新的栈顶元素
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            // 获取栈顶元素
            oldHead = top.get();
            // 将新添加的Node.next指向oldHead
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            // 获取oldHead
            oldHead = top.get();
            // stack里为空
            if (oldHead == null)
                return null;
            // 将栈顶元素的next元素指定为栈顶
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    public void print() {
        Node<E> head = top.get();
        while (head != null) {
            System.out.println(head.item);
            head = head.next;
        }
    }

    private static class Node<E> {
        public E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
