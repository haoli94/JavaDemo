package com.designpattern.TemplateMethod;


// 抽象类中可以没有抽象方法
// 有抽象方法的类必然是抽象类 使用abstract
// 用static或final修饰的方法不能声明为抽象方法

/**
 * 抽象类的成员
 * 1.成员变量: 可以是变量，也可以是常量
 * 2.成员方法: 可以是抽象方法，也可以是普通方法
 */

/**
 * 抽象类的构造方法
 * 1. 抽象类有构造方法但不能被实例化
 * 2. 由子类继承该抽象类，实例化子类
 * 3. 抽象类的子类要么实现父类的抽象方法，要么该子类继续抽象
 */


// 程序框架开发人员
public abstract class FrameWork {
    // 稳定template method
    public void Run(){
        step1();
        if(step2()){ // 支持变化 ==> 抽象函数的多态调用
            step3();
        }
        for (int i = 0; i < 5; i ++){
            step4();
        }
        step5();
    }

    private void step1(){
        System.out.println("step 1 has finished");
    }

    abstract boolean step2();

    private void step3(){
        System.out.println("step 3 has finished");
    }

    abstract void step4();

    private void step5(){
        System.out.println("step 5 has finished");
        String s1 = new String();
    }
}

