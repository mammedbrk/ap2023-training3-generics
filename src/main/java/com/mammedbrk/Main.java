package com.mammedbrk;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        MyLinkedList<String> list = new MyLinkedList<>();

        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";

        // add
        list.add(d);
        list.add(a);
        list.add(1, b);
        list.add(0, c);

        // foreach
        for (String s: list)
            System.out.println(s);
        System.out.println("#");

        // remove
        list.remove(a);
        list.remove(1);

        // iterator
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("#");

        // size
        System.out.println(list.size());
        System.out.println("#");

        // get
        System.out.println(list.get(2));
        System.out.println(list.get(0));
        System.out.println("#");

        // addAll
        List<String> collection = new ArrayList<>();
        collection.add("q");
        collection.add("w");
        collection.add("e");
        collection.add("r");
        list.addAll(collection);

        // listIterator
        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("-");
        System.out.println(list.get(listIterator.nextIndex()));
        System.out.println(list.get(listIterator.previousIndex()));
        System.out.println("-");
        listIterator.next();
        listIterator.next();
        listIterator.add("p");
        listIterator.previous();
        listIterator.remove();
        listIterator.set("l");
        list.forEach(System.out::println);
        System.out.println("#");

        // contains
        System.out.println(list.contains("y"));
        System.out.println(list.contains("q"));
        System.out.println("#");

        // sort



        // removeIf
    }
}