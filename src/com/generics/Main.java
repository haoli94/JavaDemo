package com.generics;

import java.util.ArrayList;
import java.util.List;

// 本质1 把对象集合里元素的类型推迟到创建集合的时候
// 本质2 类型参数化
// List list = new List<String>(); 错误的泛型使用方法, 泛型<String> 必须在左边


public class Main {

    public static void main(String[] args) {
        // write your code here

        Animal animal = new Cat();
        animal.born();
        animal.eat();
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.watchHouse();
        }

        if (animal instanceof Cat) {
            Cat cat = (Cat) animal;
            cat.catchMouse();
        }
        TestGeneric();
        TestGeneric2();
        TestGenericClass();
        TestGenericInterface();
        TestGenericMethod();

        ArrayList<Dog> list = new ArrayList<>();
        list.add(new Dog());
        TestGenericAll(list);

        ArrayList<Animal> list2 = new ArrayList<>();
        list.add(new Dog());
        TestGenericAll(list);
        // 协变是集合数组的多态
        // 为什么要有?通配符, 因为集合没有协变, 数组有协变
        System.out.println("数组的协变");
        Dog[] dogs = new Dog[1];
        dogs[0] = new Dog();
        TestArrayXiebian(dogs);
        System.out.println("集合没有协变, 必须类型对应");
        ArrayList<Dog> dogs2 = new ArrayList<>();
        dogs2.add(new Dog());
//        TestCollectionXiebian(dogs2);
        TestGenericAll(dogs2);
    }

    public static void TestGeneric() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add("hello");
        for (Object obj : list) {

            if (obj instanceof String) {
                String str = (String) obj;
                System.out.println(str);
            }

            if (obj instanceof Integer) {
                int i = (int) obj;
                System.out.println(i * 10);
            }
        }
    }

    public static void TestGeneric2() {
        // 任意类, 泛型通配符不是用在定义对象上，是用在方法定形参上
        ArrayList<?> list = new ArrayList<>();

        List<Animal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();

        // 向上限定, 只能接受 Dog 或者 Dog 父类的集合.
        List<? super Dog> superDogs1 = dogs;
        List<? super Dog> superDogs2 = animals;

        // 向下限定, 只能接受 Animal 或者 Animal 子类的集合.
        List<? extends Animal> extendsAnimals1 = dogs;
        List<? extends Animal> extendsAnimals2 = animals;
        List<? extends Animal> extendsAnimals3 = cats;
    }

    public static void TestGenericClass() {
        GenericType g = new GenericType();
        // 定义了泛型但没有使用, 内部所有但private 变量都是 object
        // 没有用T的时候, 里面定义的 t, e 是 object.
        g.setE("hello");
        Object content = g.getE();
        String str = (String) g.getE();
        System.out.println(str);
        System.out.println(content);

        GenericType<String, Integer> g2 = new GenericType();
        g2.setE(1);
        g2.setT("world");
        int i = g2.getE();
        String str2 = g2.getT();
    }

    public static void TestGenericInterface() {
        GenericImpl<Integer, String> genericImpl = new GenericImpl();
        genericImpl.method1(1);
        genericImpl.method2("hh");

        System.out.println("Predefined type.");

        GenericImplWithType genericImplWithType = new GenericImplWithType();
        genericImplWithType.method1("hh");
        genericImplWithType.method2(1);
    }

    public static void TestGenericMethod() {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.print("sss");
        genericMethod.print(new Dog());
        genericMethod.print(1);
    }

    public static void TestGenericAll(ArrayList<?> list) {
        // 通配符是为了解决集合的不可协变
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void TestCollectionXiebian(ArrayList<Animal> list) {
        for (Animal obj : list) {
            System.out.println(obj);
        }
    }

    public static void TestArrayXiebian(Animal[] list) {
        for (Animal obj : list) {
            System.out.println(obj);
        }
    }
}
