package com.generics;

public class Dog extends Animal {

    @Override
    public void eat() {
        System.out.println("Dog eat shit!");
    }

    public void watchHouse(){
        System.out.println("Dog watch house.");
    }
}
