package com.generics;

// 不用泛型所有参数用 object替代
class GenericNoImpl implements GenericInterface {
    @Override
    public void method1(Object e) {

    }

    @Override
    public void method2(Object t) {

    }
}

class GenericImpl<E, T> implements GenericInterface<E, T> {

    @Override
    public void method1(E e) {
        if (e instanceof Integer) {
            System.out.println("int");
        }
    }

    @Override
    public void method2(T t) {
        if (t instanceof String) {
            System.out.println("str");
        }
    }
}


class GenericImplWithType implements GenericInterface<String, Integer> {

    @Override
    public void method1(String s) {
        System.out.println("str");
    }

    @Override
    public void method2(Integer integer) {
        System.out.println("int");
    }
}