package com.streamAPI;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.
 * Collection是一种静态的内存数据结构，是主要面向内存的，存储在内存中，
 * 而Stream是和计算有关的，主要是面向CPU的，通过CPU实现计算。
 *
 * 2.
 *  1. Stream 自己不会存储元素。
 *  2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新的Stream。
 *  3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 3.
 *  1. 创建Stream，一个数据源（如，集合、数组）获取一个流。
 *  2. 中间操作，一个中间操作，对数据源的数据进行处理
 *  3. 终止操作（终端性操作）
 *
 * 4. 一旦执行终端操作，就执行中间操作链，并产生结果。之后此流不能再被使用。
 */
public class StreamAPITest {

    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1(){
        // 方式一：通过集合
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().forEach(System.out::println);
    }

    public static void test2(){
        // 方式二：通过数组
        // public static <T> Stream<T> stream(T[] array) 返回一个流
        int[] array = new int[]{1,2,4,3,5,6};
        IntStream stream = Arrays.stream(array);
        Employee e1 = new Employee(1001, "tom", 22, 2000.5);
        Employee e2 = new Employee(1002, "jerry", 25, 3000.5);
        Employee[] employees = new Employee[]{e1, e2};
        // 自定义流类型
        Stream<Employee> employeeStream = Arrays.stream(employees);
    }

    public static void test3(){
        // 方式三：通过Stream.of()
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

    public static void test4(){
        // 方式四：创建无限流
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // 遍历前10个偶数, seed是起始的数值
        Stream.iterate(0, t->t+2).limit(10).forEach(x->{
            System.out.println(x);
        });
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}

