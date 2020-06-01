package com.generics;

// interface 中所有的方法都是 public abstract
public interface GenericInterface <E, T>{
    // interface 里的变量都是 public static final 修饰， 且必须给值.
    public static final int count = 1;
    // E, T 形参是用来传入方法定义里的
    public abstract void method1(E e);
    public abstract void method2(T t);
}
