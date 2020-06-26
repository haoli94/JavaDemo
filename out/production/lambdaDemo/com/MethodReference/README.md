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



## 1.3、String类型转换

### 1.31、String与基本数据类型、包装类之间的转换

- String -->基本数据类型、包装类

方法：调用包装类的静态方法：parseXxx(Str), Integer.parseInt, Boolean.ParseBoolean

- 基本数据类型、包装类 -->String

方法：调用String重载的valueOf（xxx）或 连接一个空字符串

```java
public void test(){
    String str1 = "123";
    //将字符串型“123”转换成 数值型
    int num = Integer.parseInt(str1);
    //将数值型123 转换成 字符串
    String str2 = String.valueOf(num);
    String str3 = num + "";//此时的str3 与str1 的地址值不同
}
```

### 1.32、String 与char[] 之间的转换

- String --> char[]

方法：调用String的toCharArray()

- char[] -->String

方法：调用String的构造器
```java
public void test(){
    String str1 = "123abc";
    //将"123abc"转换为char[]形式
    char[] chars = str1.toCharArray();
    for (int i = 0;i < chars.length;i++){
        System.out.println(chars[i]);
    }
    //System.out.println(Arrays.toString(chars));//[1, 2, 3, a, b, c]
    //将char[]转换成字符串
    char[] chars1 = {'A', 'l', 'i', 'b', 'a', 'b', 'a'};
    String s = new String(chars1);
    System.out.println(s);
}
```

### 1.33、String 与byte[]（字节数组） 之间的转换

- String -->byte[]

方法：调用String的getBytes()

- byte[] -->String

方法：调用String的构造器 


```java
public void test() throws UnsupportedEncodingException {     
    String str1 = "123abc马云";
    byte[] bytes = str1.getBytes();//使用默认的字符集 进行编码   （此处为utf -8 ）
    System.out.println(Arrays.toString(bytes));//[49, 50, 51, 97, 98, 99, -23, -87, -84, -28, -70, -111]
    byte[] gbks = str1.getBytes("gbk");
    System.out.println(Arrays.toString(gbks));//[49, 50, 51, 97, 98, 99, -62, -19, -44, -58] 
    String s = new String(bytes);//使用默认的字符集 进行解码
    System.out.println(s);//123abc马云

    String s1 = new String(gbks);
    System.out.println(s1);//123abc���� 出现乱码

    String s2 = new String(gbks, "gbk");
    System.out.println(s2);//123abc马云
}
```


# 2、StringBuffer、StringBuilder

**2.1、对比String、StringBuffer、StringBuilder**

String(JDK1.0)：不可变字符序列
StringBuffer(JDK1.0)：可变字符序列、效率低、线程安全
StringBuilder(JDK 5.0)：可变字符序列、效率高、线程不安全

三者低层都是使用char[]存储

**2.2、StringBuffer类构造器**

1. StringBuffer()：**初始容量为16**的字符串缓冲区
2. **StringBuffer(int size)**：构造指定容量的字符串缓冲区 （尝使用）
3. StringBuffer(String str)：将内容初始化为指定字符串内容

**2.3、StringBuffer类的常用方法**

StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接
StringBuffer delete(int start,int end)：删除指定位置的内容
StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
StringBuffer insert(int offset, xxx)：在指定位置插入xxx
StringBuffer reverse() ：把当前字符序列逆转
public int indexOf(String str)
public String substring(int start,int end)
public int length()
public char charAt(int n )
public void setCharAt(int n ,char ch)

 **2.4、StringBuffer类扩容机制**

当append或insert时，如果原来value数组长度不够，可扩容

默认情况下，扩容为**原来的容量 \* 2 + 2**，同时将原来的数组元素复制到新的数组中。

**2.5、三者效率对比**

String(JDK1.0)：不可变字符序列 效率最低
StringBuffer(JDK1.0)：可变字符序列、效率低、线程安全
StringBuilder(JDK 5.0)：可变字符序列、效率高、线程不安全