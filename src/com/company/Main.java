package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Like l = ()->{
            System.out.println("I love Hao");
        };
        l.like();
        Like l2 = new Ilike();
        l2.like();
        Love love = new ILove();
        love.love("Ruoyan", 22);
        Love love2 = (a, b) -> {
            System.out.println("I reaaally love " + a + " aged at " + b);
        };
        love2.love("Hao", 25);
    }
}
