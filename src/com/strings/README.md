# 1、String类

```java
//源码
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];//存储 String对象的字符内容
/** Cache the hash code for the string */
private int hash; // Default to 0

/** use serialVersionUID from JDK 1.0.2 for interoperability */
private static final long serialVersionUID = -6849794470754667710L;
```

总结：

1. String类被为final 修饰，表示不可被继承。
2. String类实现了Serializable接口，表示字符串是支持序列化的。 实现了Comparable接口，表示String可以比较大小
3.String类在内部定义了final char[] value ,用于存储字符串数据
4.String：代表了不可变的字符序列 ，即不可变性 体现：①当对字符串重新赋值时，需要重新在字符串常量池中开辟一个value存放，不能再原来的value上进行覆盖。②：当对现有的字符串进行连接操作时，也需要重新指定赋值，不能再原来的value上赋值。③：当对现有字符串进行replace（）操作时，同①②。
5.通过字面量定义的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池（方法区中）中。
6.字符串常量池中不会存储相同内容的字符串。

## 1.1、String对象的创建 String类的构造器String str = "hello";

``` java
String str = "hello";

//本质上this.value = new char[0];
String s1 = new String();

//this.value = original.value;
String s2 = new String(String original);

//this.value = Arrays.copyOf(value, value.length);
String s3 = new String(char[] a);

String s4 = new String(char[] a,int startIndex,int count);

```

String实例化方式：

①通过字面量定义的方式 ：声明在字符串常量池中
②通过 new + 构造器的方式 ：保存的地址值是数据在堆空间中开辟的空间对应的地址值字符串连接特性 

①常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。 只要其中有一个是变量，结果在堆中 

②如果拼接的结果调用intern()方法，返回值就在常量池中

## 1.2、String常用方法

* int length()：返回字符串的长度： return value.length
* char charAt(int index)： 返回某索引处的字符return value[index]
* boolean isEmpty()：判断是否是空字符串：return value.length == 0
* String toLowerCase()：使用默认语言环境，将 String 中的所有字符转换为小写
* String toUpperCase()：使用默认语言环境，将 String 中的所有字符转换为大写
* String trim()：返回字符串的副本，忽略前导空白和尾部空白
* boolean equals(Object obj)：比较字符串的内容是否相同
* boolean equalsIgnoreCase(String anotherString)：与equals方法类似，忽略大小写
* String concat(String str)：将指定字符串连接到此字符串的结尾。 等价于用“+”
* int compareTo(String anotherString)：比较两个字符串的大小 涉及到字符串排序
* String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从 beginIndex开始截取到最后的一个子字符串。
* String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。
* boolean contains(CharSequence s)：当且仅当此字符串包含指定的 char 值序列时，返回 true
* int indexOf(String str)：返回指定子字符串在此字符串中第一次出现处的索引
* int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
* int lastIndexOf(String str)：返回指定子字符串在此字符串中最右边出现处的索引
* int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后 一次出现处的索引，从指定的索引开始反向搜索 注：indexOf和lastIndexOf方法如果未找到都是返回-1
* boolean endsWith(String suffix)：测试此字符串是否以指定的后缀结束
* boolean startsWith(String prefix)：测试此字符串是否以指定的前缀开始
* boolean startsWith(String prefix, int toffset)：测试此字符串从指定索引开始的 子字符串是否以指定前缀开始
* String replace(char oldChar, char newChar)：返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。

* String replace(CharSequence target, CharSequence replacement)：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。

* String replaceAll(String regex, String replacement) ： 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。

* String replaceFirst(String regex, String replacement) ： 使用给定 的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。

* boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式。
* String[] split(String regex)：根据给定正则表达式的匹配拆分此字符串。
* String[] split(String regex, int limit)：根据匹配给定的正则表达式来拆分此 字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。

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