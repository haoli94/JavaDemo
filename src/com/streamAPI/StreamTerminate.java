package com.streamAPI;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTerminate {
    public static void main(String[] args){
//        test1();
//        test2();
        test3();
    }

    // 1. 查找与匹配
    public static void test1(){
        List<Employee> employeeList = EmployeeData.getEmployees();
        Optional<Employee> maxEmployee = employeeList.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(maxEmployee);

        // 外部迭代 使用Collections接口
        employeeList.forEach(System.out::println);
        // 内部迭代 使用Stream API内部迭代
        employeeList.stream().forEach(System.out::println);
    }

    // 2. 归约 reduce
    public static void test2(){
        List<Employee> employeeList = EmployeeData.getEmployees();
        // Optional<Double> sum = employeeList.stream().map(e -> e.getSalary()).reduce(Double::sum);
        Double sum = employeeList.stream().map(e -> e.getSalary()).reduce((double) 100000, (s1, s2) -> s1 + s2);
        System.out.println(sum);
    }

    // 3. 收集
    public static void test3(){
        List<Employee> employeeList = EmployeeData.getEmployees();
        List<Employee> employees = employeeList.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        System.out.println(employees);
    }
}
