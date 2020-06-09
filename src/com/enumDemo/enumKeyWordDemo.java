package com.enumDemo;


enum Season1 {
  // 1. 提供当前枚举类的对象, 多个对象之间用","隔开, 末尾对象以";"结尾.
  SPRING("Spring", "1"),
  SUMMER("Summer", "2"),
  AUTUMN("Autumn", "3"),
  WINTER("Winter", "4");
  // 2. 声明season对象的属性: private final 修饰
  private final String SeasonName;
  private final String SeasonDesc;

  private Season1(String seasonName, String seasonDesc) {
    this.SeasonName = seasonName;
    this.SeasonDesc = seasonDesc;
  }

  public String getSeasonName() {
    return SeasonName;
  }

  public String getSeasonDesc() {
    return SeasonDesc;
  }

//  @Override
//  public String toString() {
//    return "Season{" +
//        "SeasonName='" + SeasonName + '\'' +
//        ", SeasonDesc='" + SeasonDesc + '\'' +
//        '}';
//  }
// enum 关键字 继承自 class java.lang.Enum, 已经重写了toString方法
}

public class enumKeyWordDemo {
  public static void main(String[] args) {
    Season1 spring = Season1.SPRING;
    System.out.println(spring);
    System.out.println(spring.getSeasonName());
    System.out.println(spring.getSeasonDesc());
    System.out.println(Season1.class.getSuperclass());
  }
}
