package com.designpattern.PrivateClassAcess;

class t{

    private int value = 0;

    public t(){

    }

    public t(t t1){
        // 同一个类的对象即使不是同一个实例也可以互相访问对方的私有与受保护成员。
        // 这是由于在这些对象的内部具体实现的细节都是已知的。
        this.value = t1.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "t{" +
                "value=" + value +
                '}';
    }
}


public class test {
    public static void main(String[] args){
        t t1 = new t();
        t1.setValue(10);
        t t2 = new t(t1);
        System.out.println(t2);
    }
}
