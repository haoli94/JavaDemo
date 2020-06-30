## 一.异常概述

> 异常：在Java语言中，将程序执行中发生的不正常情况称为“异常” (开发过程中的语法错误和逻辑错误不是异常)

## 1.1 分类

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200406152053858.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMTEwMzg5,size_16,color_FFFFFF,t_70)

```text
ERROR: Java虚拟机无法解决的严重问题。如：JVM系统内部错误、资源 耗尽等严重情况。比如：StackOverflowError（栈溢出）和OOM（堆溢出）。

Exception: 其它因编程错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理。例如：空指针访问 试图读取不存在的文件 网络连接中断 数组角标越界
```

## 1.2 编译时异常：

>
指编译器要求必须处置的异常。即程序在运行时由于外界因素造成的一 般性异常。编译器要求Java程序必须捕获或声明所有编译时异常。

## 1.3 运行时异常：

> 是指编译器不要求强制处置的异常。一般是指编程时的逻辑错误，是程序员应该积极避免其出现的异常。java.lang.RuntimeException类及它的子类都是运行时异常

## 二、常见异常
运行时异常举例：NullPointerException（空指针）
ArrayIndexOutOfBoundsException（数组角标越界）
ClassCastException（类型转换异常）
NumberFormatException（数字格式异常）：字符串转换成数值时
InputMismatchException（输入不匹配异常）
ArithmeticException（运算条件异常）：除0

##	三、异常处理机制：抓抛模型
①：过程一“抛”：程序在执行的过程中，一旦出现异常，就会在异常代码处生成一个对应异常类的对象，并将此对象抛出。结束其后代码的执行。
②：过程二：在“抓”：可以理解为异常的处理方式

## 3.1机制一：try —catch—finally

## 3.2机制二：throws + 异常类型

> 如果一个方法(中的语句执行时)可能生成某种异常，但是并不能确定如何处理这种异常，则此方法应显示地声明抛出异常，表明该方法将不对这些异常进行处理， 而由该方法的调用者负责处理。

## 四、手动抛出异常（通过throw语句实现抛出操作）

> 可以抛出的异常必须是Throwable或其子类的实例。
>
> 例：throw new Runtime Exception(“输入数据非法”)；