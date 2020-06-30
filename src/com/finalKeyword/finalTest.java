package com.finalKeyword;
import java.util.ArrayList;
import java.util.List;

public class finalTest {

    /**
     * 静态代码块：用staitc声明，jvm加载类时执行，仅执行一次
     * 构造代码块：类中直接用{}定义，每一次创建对象时执行。
     * 执行顺序优先级：静态块,main(),构造块,构造方法。
     */

    // final int value = 0;
    // 如果是 static final 全局常量，可以考虑赋值的位置：显示初始化，静态代码块中初始化
    static final int classVal;
    // final修饰一个属性：可以考虑赋值的位置：显示初始化，构造器代码块中初始化、构造器中初始化
    final int instanceVal;
    final int constructorVal;
    // static 静态代码块
    static {
        // instanceVal 后面创建具体类才有，不能在静态代码块里赋值
        classVal = 0;
    }
    // 构造代码块
    {
        instanceVal = 0;
    }

    public finalTest(){
        // 所有构造器中都要初始化
        constructorVal = 0;
    }

    public finalTest(int n){
        constructorVal = n;
    }

    public static void add(final int num){
        System.out.println(num);
        // num++;
    }

    // final修饰方法形参时，表明此形参是一个常量,
    // 如果是基础类型，不能被reassign或改变，
    // 如果是引用类型，则不能被reassign.
    public static void addItemToList(final List<Integer> list){
        list.add(0);
        System.out.println(list);
    }

    public static void reassignList(final List<Integer> list){
        // list = new ArrayList<>();
    }

    public static void main(String[] args){
        // final修饰局部变量：可以延迟赋值，但是一旦赋值不能更改。
        final int num;
        num = 1;
        // num ++;
        addItemToList(new ArrayList<>());
    }
}
