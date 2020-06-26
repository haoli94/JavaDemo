### 一、StreamAPI简介

　Stream API (java.util.stream)把真正的函数式编程风格引入到java。这是目前对java类最好的补充，因为Stream API 可以极大的提高Java程序员的生产力，让程序员写出高效、干净、简洁的代码。	

　Stream和Collection的区别：

​	Collection是一种静态的内存数据结构，而Stream是和计算有关的。前者是主要面向内存的，存储在内存中，后者主要是面向CPU的，通过CPU实现计算。

​	Stream 是数据渠道，用于操作数据源（集合，数组等）所生成的元素序列。

```text
1. Stream 自己不会存储元素。
2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新的Stream。
3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
```

1. 创建Stream,

   一个数据源（如，集合、数组）获取一个流。

2. 中间操作

   一个中间操作，对数据源的数据进行处理

3. 终止操作（终端性操作）

   **一旦执行终端操作，就执行中间操作链，并产生结果**。之后此流**不能再被使用**。

### 二、创建Stream

创建Stream的方式一：通过集合

Java8的Collection接口被拓展，提供了两个获取流的方法。

假设一个Person类,

```java
default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
}

default Stream<E> parallelStream() {
    return StreamSupport.stream(spliterator(), true);
}
// 并行流中的顺序可能是随机的。
```

```java
public static void test1(){
    // 方式一：通过集合
    List<Employee> employees = EmployeeData.getEmployees();
}
```

方式二：通过数组 Arrays.stream(array)

```java
public static void test2(){
    // 方式二：通过数组
    // public static <T> Stream<T> stream(T[] array) 返回一个流
    int[] array = new int[]{1,2,4,3,5,6};
    IntStream stream = Arrays.stream(array);
    Employee e1 = new Employee(1001, "tom", 22, 2000.5);
    Employee e2 = new Employee(1002, "jerry", 25, 3000.5);
    Employee[] employees = new Employee[]{e1, e2};
    // 自定义流类型
    Stream<Employee> employeeStream = Arrays.stream(employees);
}
```

方式三：通过Stream.of()

```java
public static void test3(){
    // 方式三：通过Stream.of()
    Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
}
```

方式四：创建无限流

```java
public static void test4(){
    // 方式四：创建无限流
    // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
    // 遍历前10个偶数, seed是起始的数值
    Stream.iterate(0, t->t+2).limit(10).forEach(x->{
        System.out.println(x);
    });
    // public static<T> Stream<T> generate(Supplier<T> s)
    Stream.generate(Math::random).limit(10).forEach(System.out::println);
}
```

### 三、中间操作

　多个**中间操作**可以连接起来形成一个**流水线**，除非流水线上触发终止操作，**否则中间操作不会执行任何的处理返回任何的结果，而在终止操作时一次性全部处理**，称为**惰性求值**：

1. 筛选与切片

```
filter(predicate p): 接收lambda, 从流中排除或者选取某些元素
distinct(): 筛选，通过流所生成的元素的hashCode()和equals()去除重复元素
limit(): 截断流，使其元素不超过给定的数量
skip(): 跳过元素，返回一个扔掉了前n个元素的流，若n>流中元素数量，则返回一个空流。
```

 2. 映射

    map(function f) 接收一个函数作为参数，将元素转换为其他形式或者提取信息，该函数会被应用到每个元素身上，并将其映射成为一个新元素。

    flatMap(function f) 接收一个函数作为参数，将流中每个值都转换成另一个流，然后把所有流连接成一个流。

    ```java
    public static void test3(){
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);
        // stream of streams
        list.stream().map(StreamMid::fromStringToStream).forEach(
                s->s.forEach(System.out::println));
        System.out.println();
        // flat nested streams
        list.stream().flatMap(StreamMid::fromStringToStream).forEach(System.out::println);
    }
    ```

 3. 排序

```
sorted()                : 产生一个新流，自然排序, 要求stream中的类实现comparable.
sorted(Comparator com)  : 产生一个新流，按比较器排序
```

```java
public static void test4(){
    List<Employee> employeeList = EmployeeData.getEmployees();
    Stream<Employee> employeeStream = employeeList.stream();
    employeeStream.sorted((e1, e2)-> e1.getAge() - e2.getAge()).limit(1).forEach(System.out::println);
    Stream<Employee> employeeStream1 = employeeList.stream();
        employeeStream1.sorted((e1, e2)-> {
            if (e1.getSalary() - e2.getSalary() > 0){
                return 1;
            } else {
                return -1;
            }
        }).limit(1).forEach(System.out::println);

        Stream<Employee> employeeStream2 = employeeList.stream();
        employeeStream2.sorted((e1, e2)-> Double.compare(e2.getSalary(),e1.getSalary())).limit(1).forEach(System.out::println);

}
```

### 四、终止操作

**1.匹配与查找**

allmatch(Predicate p) --检查是否匹配所有元素

anymatch(Predicate p) --检查是否至少匹配一个元素

noneMatch(Predicate p) --检查是否没有匹配该条件的元素

 返回Boolean

Predicate 是一个函数接口，有test方法。 就是判断条件的函数。

findFirst() -- 返回第一个元素。

findAny() -- 返回任意一个元素。

count() -- 返回流中元素总个数。返回的是long

max(Comparator c) | min(Comparator c) 返回流中最大最小

forEach(Consumer c) 内部迭代

```java
// 1. 查找与匹配
public static void test1(){
    List<Employee> employeeList = EmployeeData.getEmployees();
    Optional<Employee> maxEmployee = employeeList.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
    System.out.println(maxEmployee);

    // 外部迭代 使用Collections接口
    employeeList.forEach(System.out::println);
    // 内部迭代 使用Stream API内部迭代
    employeeList.stream().forEach(System.out::println);
}
```

**2. reduce**

```java
T reduce(T identity, BinaryOperator<T> accumulator); identity是初始值, 将stream里值加到一起
Optional<T> reduce(BinaryOperator<T> accumulator);
```

```java
// 2. 归约 reduce
public static void test2(){
    List<Employee> employeeList = EmployeeData.getEmployees();
    // Optional<Double> sum = employeeList.stream().map(e -> e.getSalary()).reduce(Double::sum);
    Double sum = employeeList.stream().map(e -> e.getSalary()).reduce((double) 100000, (s1, s2) -> s1 + s2);
    System.out.println(sum);
}
```

3. **收集**

```
collect(Collector c) 将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
```

Collector 接口中的方法的实现决定了如何对流执行收集的操作（收集到List、Set、Map）

```java
// 3. 收集
public static void test3(){
    List<Employee> employeeList = EmployeeData.getEmployees();
    List<Employee> employees = employeeList.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
    System.out.println(employees);
}
```

Collectors.toList(), Collectors.toSet() 不是Collections.