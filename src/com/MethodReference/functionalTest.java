package com.methodReference;

import com.streamAPI.Employee;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class functionalTest {
    public static void main(String[] args){
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }


    public static void test1(){
        happyTime(1000.50, new Consumer<Double>() {
            @Override
            public void accept(Double money) {
                System.out.println("life is hard for " + money);
            }
        });
        happyTime(1000.50, x -> System.out.println(x));
        happyTime(1000.50, System.out::println);
    }

    public static void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }

    public static void test2(){
        // 情况一：对象::实例方法
        // Consumer中的accept方法(T t)
        // PrintStream中的System.out.println(T t)
        PrintStream ps = System.out;
        Consumer<String> consumer = ps::println;
        consumer.accept("hello");
    }

    public static void test3(){
        // Supplier中的 T get()
        // employee中的 String getName()
        Employee employee = new Employee(1000,"hao",25,1000.1);
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());
        System.out.println("************************");
        Supplier<String> supplier2 = employee::getName;
        System.out.println(supplier2.get());
    }

    // 情况二: 类 ::静态方法
    // Comparator中的int compare(T t1, T t2);
    // Integer中的int compare(T t1, T t2);
    public static void test4(){
        Comparator<Integer> comp1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(comp1.compare(1, 2));
        System.out.println("********************");
        Comparator<Integer> comp2 = Integer::compare;
        System.out.println(comp2.compare(1, 2));
    }

    // Function中的R apply(T t)
    // Math中的long round(Double d)
    public static void test5(){
        Function<Double, Long> func1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double input) {
                return Math.round(input);
            }
        };
        System.out.println(func1.apply(1000.0));
        System.out.println("****************");
        Function<Double, Long> func2 = Math::round;
        System.out.println(func1.apply(2000.0));
    }

    // 情况三: 类::实例方法
    // Comparator中的int compare(T t1, T t2);
    // String中的 int t1.compareTo(T t2);
    // 参数列表和返回值不能完全匹配上，
    // Comparator的中,
    // compare(T t1, T t2){
    //      return t1.compareTo(t2);
    // }
    public static void test6(){
        Comparator<String> comp1 = (s1, s2) -> s1.compareTo(s2);
        Comparator<String> comp2 = String::compareTo;
        System.out.println(comp1.compare("abc", "bcd"));
        System.out.println("******************************");
        System.out.println(comp2.compare("abc", "bcd"));
    }
}
