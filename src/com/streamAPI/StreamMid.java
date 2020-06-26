package com.streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


// Stream中间操作

public class StreamMid {
    // 1. 筛选与切片
    public static void main(String[] args){
        //  test1();
        //  test2();
        //  test3();
        test4();
    }

    public static void test1(){
        List<Employee> employeeList = EmployeeData.getEmployees();
        Stream<Employee> employeeStream = employeeList.stream();
        //  filter(predicate p): 接收lambda, 从流中排除或者选取某些元素
        employeeStream.filter(x->x.getSalary()>5000).forEach(System.out::println);

        //  skip(): 跳过元素，返回一个扔掉了前n个元素的流，若n>流中元素数量，则返回一个空流。
        System.out.println();
        Stream<Employee> employeeStream2 = employeeList.stream();
        employeeStream2.skip(10).forEach(System.out::println);

        employeeList.add(new Employee(1006, "Zuck", 35, 2500.65));
        employeeList.add(new Employee(1006, "Zuck", 35, 2500.65));
        employeeList.add(new Employee(1006, "Zuck", 35, 2500.65));

        //  distinct(): 筛选，通过流所生成的元素的hashCode()和equals()去除重复元素
        System.out.println();
        Stream<Employee> employeeStream3 = employeeList.stream();
        employeeStream3.forEach(System.out::println);

        System.out.println();
        Stream<Employee> employeeStream4 = employeeList.stream();
        employeeStream4.distinct().forEach(System.out::println);
        // Employee 类要实现equals 和 hashCode
    }

    // 2. 映射
    public static void test2(){
        List<Employee> employeeList = EmployeeData.getEmployees();
        Stream<Employee> employeeStream = employeeList.stream();
        Stream<String> names = employeeStream.map(Employee::getName);
        names.filter(name -> name.length() > 3).forEach(System.out::println);
    }

    public static void test3(){
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);
        // stream of streams
        list.stream().map(StreamMid::fromStringToStream).forEach(
                s->s.forEach(System.out::println));
        System.out.println();
        // flat nested streams
        list.stream().flatMap(StreamMid::fromStringToStream).forEach(System.out::println);
    }

    public static Stream<Character> fromStringToStream(String str){
        List<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    // 3. 排序
    public static void test4(){
        List<Employee> employeeList = EmployeeData.getEmployees();
        Stream<Employee> employeeStream = employeeList.stream();
        employeeStream.sorted((e1, e2)-> {
            if (e1.getSalary() - e2.getSalary() > 0){
                return 1;
            } else {
                return -1;
            }
        }).limit(1).forEach(System.out::println);

        Stream<Employee> employeeStream2 = employeeList.stream();
        employeeStream2.sorted((e1, e2)-> Double.compare(e2.getSalary(),e1.getSalary())).limit(1).forEach(System.out::println);
    }
}
