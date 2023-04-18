package com.mammedbrk;

import java.util.*;
import java.util.function.Predicate;

public class MyLinkedList<E> implements Iterable<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;


    private boolean invalidIndex(int index) {
        return index < 0 || index > size;
    }

    private Node<E> node(int index) {
        if (invalidIndex(index))
            return null;
        Node<E> node = first;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    private void unlink(Node<E> node) { //todo fig bugs
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        }
        else {
            first = nextNode;
        }
        if (nextNode != null) {
            nextNode.prev = prevNode;
        }
        else {
            last = prevNode;
        }
        size--;
    }

    // Add object to end of the list
    public void add(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(e, l, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    // Add object in a custom index
    public boolean add(int index, E e) {
        if (invalidIndex(index))
            return false;

        if (index == size)
            add(e);
        else {
            Node<E> newNode = new Node<>(e, null, null);
            Node<E> nextNode = node(index);
            Node<E> prevNode = (nextNode == null)? null: nextNode.prev;

            if (prevNode == null)
                first = newNode;
            else {
                newNode.prev = prevNode;
                prevNode.next = newNode;
            }
            if (nextNode == null) {
                last = newNode;
            }
            else {
                nextNode.prev = newNode;
                newNode.next = nextNode;
            }
            size++;
        }
        return true;
    }

    // Remove object by getting it
    public boolean remove(E e) {
        if (e == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.value == null) {
                    unlink(node);
                    return true;
                }
            }
        }
        else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.value != null && node.value.equals(e)) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    // Remove object by getting its index
    public boolean remove(int index) {
        Node<E> node = node(index);
        if (node == null)
            return false;
        unlink(node);
        return true;
    }

    // Return size of list
    public int size() {
        return size;
    }

    // Return object by getting its index
    public E get(int index) {
        Node<E> node = node(index);
        if (node == null)
            return null;
        return node.value;
    }

    // Check if object is contained in list
    public boolean contains(E e) {
        if (e == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.value == null) {
                    return true;
                }
            }
        }
        else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.value != null && node.value.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Add all elements of a collection
    public void addAll(Collection<? extends E> c) {
        for (E e: c) {
            add(e);
        }
    }

    // Return ListIterator
    public ListIterator<E> listIterator(int index) {
        if (invalidIndex(index))
            return null;
        return new MyListIterator(index);
    }

    // Return Iterator
    public Iterator<E> iterator() {
        return new MyListIterator(0);
    }

    public void sort(Comparator<? super E> comparator) {
        Object[] array = new Object[size];
        int index = 0;
        for (Node<E> node = first; node != null; node = node.next) {
            array[index++] = node.value;
        }
        Arrays.sort(array, (Comparator) comparator);
        ListIterator<E> i = this.listIterator(0);
        for (Object o : array) {
            i.set((E) o);
            i.next();
        }
    }

    public void removeIf(Predicate<E> p) {
        ListIterator listIterator = listIterator(0);
        while (listIterator.hasNext()) {
            if (p.test((E) listIterator.next())) {
                listIterator.remove();
            }
        }
    }

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private class MyListIterator implements ListIterator<E> {
        private Node<E> current;
        private Node<E> next;
        private int nextIndex;

        public MyListIterator(int nextIndex) {
            this.next = node(nextIndex);
            this.nextIndex = nextIndex;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                return null;
            current = next;
            next = next.next;
            nextIndex++;
            return current.value;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                return null;
            if (next == null)
                current = next = last;
            else
                current = next = next.prev;
            nextIndex--;
            return current.value;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (current == null)
                return;
            Node<E> lastNext = current.next;
            unlink(current);
            if (next == current)
                next = lastNext;
            else
                nextIndex--;
            current = null;
        }

        @Override
        public void set(E e) {
            next.value = e;
        }

        @Override
        public void add(E e) {
            MyLinkedList.this.add(nextIndex, e);
        }
    }
}
