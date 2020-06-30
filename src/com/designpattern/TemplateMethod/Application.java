package com.designpattern.TemplateMethod;

public class Application extends FrameWork{
    @Override
    boolean step2() {
        return true;
    }

    @Override
    void step4() {
        System.out.println("step 4 has finished");
    }
}
