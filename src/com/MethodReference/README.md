# 1、MethodReference

| 函数式接口 | 参数类型 | 返回类型 | 用途 |
| :----:| :---: | :----: | :----: |
| Consumer<T> | T | void | 消费者类型接口，包含方法accept(T t); |
| Supplier<T> | void | T | 供给型接口，包含方法T get(); |
| Function<T,R> | T | R | 函数型接口, 对类型为T的对象应用操作, 并返回R类型结果，包含方法 R apply(T t) |
| Predicate<T> | T | boolean | 断定型接口，确定类型为T的对象是否满足某种约束 |

## 1.1、定义

方法引用是Lambda表达式深层次的表达，换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，**通过方法名来指向一个方法**。

## 1.2、使用情景

当要传递给Lambda体的操作，已经有了实现的方法，可以使用方法引用。

## 1.3、要求及形式

1. 对象::实例方法名

   ```java
   public static void test2(){
       // 情况一：对象::实例方法
       // Consumer中的accept方法(T t)
       // PrintStream中的System.out.println(T t)
       PrintStream ps = System.out;
       Consumer<String> consumer = ps::println;
       consumer.accept("hello");
   }
   
   public static void test3(){
       // Supplier中的 T get()
       // employee中的 String getName()
       Employee employee = new Employee(1000,"hao",25,1000.1);
       Supplier<String> supplier = () -> employee.getName();
       System.out.println(supplier.get());
       System.out.println("************************");
       Supplier<String> supplier2 = employee::getName;
       System.out.println(supplier2.get());
   }
   ```

   

2. 类::静态方法名

   实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法参数列表和返回值类型保持一致。可以是父类。 （一般情况）

   ```java
   // 情况二: 类 ::静态方法
   // Comparator中的int compare(T t1, T t2);
   // Integer中的int compare(T t1, T t2);
   public static void test4(){
       Comparator<Integer> comp1 = (t1, t2) -> Integer.compare(t1, t2);
       System.out.println(comp1.compare(1, 2));
       System.out.println("********************");
       Comparator<Integer> comp2 = Integer::compare;
       System.out.println(comp2.compare(1, 2));
   }
   
   // Function中的R apply(T t)
   // Math中的long round(Double d)
   public static void test5(){
       Function<Double, Long> func1 = new Function<Double, Long>() {
           @Override
           public Long apply(Double input) {
               return Math.round(input);
           }
       };
       System.out.println(func1.apply(1000.0));
       System.out.println("****************");
       Function<Double, Long> func2 = Math::round;
       System.out.println(func1.apply(2000.0));
   }
   ```

3. 类::实例方法名（特殊情况）

   Comparator中的int compare(T t1, T t2);
   String中的 int t1.compareTo(T t2);
   参数列表和返回值不能完全匹配上，但是
   
   Comparator的中, compare(T t1, T t2){
         return t1.compareTo(t2);
   } 也是可以使用的
   
   ```java
   // 情况三: 类::实例方法
   // Comparator中的int compare(T t1, T t2);
   // String中的 int t1.compareTo(T t2);
   // 参数列表和返回值不能完全匹配上，
   // Comparator的中,
   // compare(T t1, T t2){
   //      return t1.compareTo(t2);
   // }
   public static void test6(){
       Comparator<String> comp1 = (s1, s2) -> s1.compareTo(s2);
       Comparator<String> comp2 = String::compareTo;
       System.out.println(comp1.compare("abc", "bcd"));
       System.out.println("******************************");
       System.out.println(comp2.compare("abc", "bcd"));
   }
   ```

# 2、Constructor Reference Method

**2.1、构造器引用**

```java
public static void test1(){
    // 空参构造器
    Supplier<Employee> supplier = Employee::new;
    System.out.println(supplier.get());
}

public static void test2(){
    // 一参构造器 Function R apply(T t);
    Function<Integer,Employee> func = Employee::new;
    System.out.println(func.apply(1010));
}

public static void test3(){
    // 两参构造器 BiFunction R apply(T t, U u);
    BiFunction<Integer, String, Employee> biFunc = Employee::new;
    System.out.println(biFunc.apply(1010, "hao"));
}
```

**2.2、数组引用**

将数组如String[]看作是一个特殊的类

```java
// Function 中的 R apply(T t);
// 将数组如String[]看作是一个特殊的类
public static void test4(){
    Function<Integer, String[]> func1 = length -> new String[length];
    String[] arr1 = func1.apply(10);
    System.out.println(Arrays.toString(arr1));

    System.out.println("****************");

    Function<Integer, String[]> func2 = String[]::new;
    String[] arr2 = func2.apply(5);
    System.out.println(Arrays.toString(arr2));
}
```