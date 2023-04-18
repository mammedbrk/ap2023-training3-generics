package com.mammedbrk;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class MyLinkedList<E> {
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

        if (prevNode != null)
            prevNode.next = nextNode;
        if (nextNode != null)
            nextNode.prev = prevNode;
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
            Node<E> prevNode = nextNode.prev;

            if (prevNode == null)
                first = newNode;
            else {
                newNode.prev = prevNode;
                prevNode.next = newNode;
            }
            nextNode.prev = newNode;
            newNode.next = nextNode;
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
                if (node.value.equals(e)) {
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
                if (node.value.equals(e)) {
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
            Node<E> returnNode = next;
            next = next.next;
            nextIndex++;
            return returnNode.value;
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
                next = last;
            else
                next = next.prev;
            nextIndex--;
            return next.value;
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

        }

        @Override
        public void set(E e) {
        }

        @Override
        public void add(E e) {
        }
    }
}
