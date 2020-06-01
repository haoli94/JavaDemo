package com.generics;

/*
泛型类的定义:
<>里面的东西是泛型的形参
T就是泛型的形参名，遵循Java 标识符就可以
实现泛型的时候传入的 String Integer 什么的是实参数, 实际的Type
通常是一个大写字母 like T, E 类名
泛型通常可以定义多个，多个之间用逗号隔开
 */

public class GenericType<T, E> {
    // 成员变量
    T t;
    E e;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
