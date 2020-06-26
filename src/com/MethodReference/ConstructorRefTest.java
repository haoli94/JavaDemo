package com.MethodReference;

import com.streamAPI.Employee;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorRefTest {
    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1(){
        // 空参构造器
        Supplier<Employee> supplier = Employee::new;
        System.out.println(supplier.get());
    }

    public static void test2(){
        // 一参构造器 Function R apply(T t);
        Function<Integer,Employee> func = Employee::new;
        System.out.println(func.apply(1010));
    }

    public static void test3(){
        // 两参构造器 BiFunction R apply(T t, U u);
        BiFunction<Integer, String, Employee> biFunc = Employee::new;
        System.out.println(biFunc.apply(1010, "hao"));
    }

    // 数组引用
    // Function 中的 R apply(T t);
    // 将数组如String[]看作是一个特殊的类
    public static void test4(){
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(10);
        System.out.println(Arrays.toString(arr1));

        System.out.println("****************");

        Function<Integer, String[]> func2 = String[]::new;
        String[] arr2 = func2.apply(5);
        System.out.println(Arrays.toString(arr2));
    }
}
