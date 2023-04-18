package com.mammedbrk;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyLinkedList<User> list = new MyLinkedList<>();
        User akbar = new User("akbar", 40);
        list.add(akbar);
        list.add(new User("mohsen", 46));
        list.add(new User("morteza", 50));
        list.add(new User("gholamreza", 48));

        System.out.println(list.size());

        System.out.println(list.remove(akbar));

        System.out.println(list.contains(akbar));

        System.out.println(list.remove(12));

        System.out.println(list.get(9));

        list.add(2, new User("abbas", 47));

        System.out.println(list.get(2).getName());

        System.out.println(list.remove(list.get(4)));

        List<User> newList = new LinkedList<>();
        newList.add(new User("mohammadreza", 39));
        newList.add(new User("pejman", 43));
        newList.add(new User("abdolhamid", 57));

        list.addAll(newList);


        /*List<Integer> list = new LinkedList<>();
        list.add(null);
        System.out.println(list.contains(null));*/

        /*User user = null;
        System.out.println(user == null);*/
        
        /*User user = new User("akbar", 40);

        User user2 = user;

        user = new User("mohsen", 46);

        System.out.println(user2.getName());
        System.out.println(user.getName());*/
    }
}