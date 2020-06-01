package com.generics;

public class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("Cat eat fish.");
    }

    public void catchMouse(Integer...varargs){
        System.out.println("Cat catch Mouse.");
    }
}
