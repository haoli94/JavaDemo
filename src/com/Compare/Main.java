package com.compare;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<PersonComparable> PersonComparableList = new ArrayList<>(Arrays.asList(new PersonComparable("Hao", 25),
                new PersonComparable("Ruoyan", 22),
                new PersonComparable("JQ", 24),
                new PersonComparable("Ruoyu", 23),
                new PersonComparable("ABC", 22)));
        Collections.sort(PersonComparableList);
        for (PersonComparable p : PersonComparableList){
            System.out.println(p);
        }
        System.out.println();

        List<Person> PersonList = new ArrayList<>(Arrays.asList(new Person("Hao", 25),
                new Person("Ruoyan", 22),
                new Person("JQ", 24),
                new Person("Ruoyu", 23),
                new Person("ABC", 22)));
        Collections.sort(PersonList, new PersonCompartor());
        for (Person p : PersonList){
            System.out.println(p);
        }
        System.out.println();

        Comparator<Person> sortByName = (Person p1, Person p2) -> p1.getName().compareTo(p2.getName());
        // Comparator<Person> sortByName = (p1, p2) -> p1.getName().compareTo(p2.getName());
        // Lambda 表达式
        Collections.sort(PersonList, sortByName);
        for (Person p : PersonList){
            System.out.println(p);
        }
        System.out.println();

        Collections.sort(PersonList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        for (Person p : PersonList){
            System.out.println(p);
        }
        System.out.println();

        List<Integer> list2 = new ArrayList<>(Arrays.asList(
                7,9,8,11,1,2,3,4,5,6
        ));
        Collections.sort(list2, Comparator.reverseOrder());
        for (int n : list2){
            System.out.println(n);
        }
    }
}
