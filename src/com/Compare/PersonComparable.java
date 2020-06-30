package com.compare;

public class PersonComparable implements Comparable<PersonComparable> {
    private String name;
    private int age;

    public PersonComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "PersonComparable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(PersonComparable o) {
        if (age == o.getAge()){
            return name.compareTo(o.getName());
        }
        return this.age - o.getAge();
    }
}
