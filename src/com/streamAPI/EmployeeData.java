package com.streamAPI;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1001, "马化腾", 34, 6000.65));
        list.add(new Employee(1002, "Jack Ma", 12, 9579.34));
        list.add(new Employee(1003, "Richard", 33, 3000.65));
        list.add(new Employee(1004, "雷军", 26, 8600.65));
        list.add(new Employee(1005, "Bill Gates", 54, 9699.65));
        list.add(new Employee(1006, "Zuck", 35, 2500.65));
        return list;
    }
}
