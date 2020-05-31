package com.sortDemo;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        List<String> list = new ArrayList<>(Arrays.asList(
                "hao", "ruoyan", "ruoyu", "jingchi", "abc", ""
        ));
        Comparator<String> sortByName = (String s1, String s2) -> s1.compareTo(s2);
        // s1 > s2 return True需要换, 否则false用换, 默认增序 ASC
        Collections.sort(list, sortByName);
        for (String s : list){
            System.out.println(s);
        }
        List<Integer> list2 = new ArrayList<>(Arrays.asList(
                7,9,8,11,1,2,3,4,5,6
        ));
        Collections.sort(list2, Comparator.reverseOrder());
        for (int n : list2){
            System.out.println(n);
        }
    }
}
