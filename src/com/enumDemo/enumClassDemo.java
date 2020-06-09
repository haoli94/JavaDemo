package com.enumDemo;

class Season {
  private final String SeasonName;
  private final String SeasonDesc;

  private Season(String seasonName, String seasonDesc) {
    this.SeasonName = seasonName;
    this.SeasonDesc = seasonDesc;
  }

  public static final Season SPRING = new Season("Spring", "1");
  public static final Season SUMMER = new Season("Summer", "2");
  public static final Season AUTUMN = new Season("Autumn", "3");
  public static final Season WINTER = new Season("Winter", "4");

  public String getSeasonName() {
    return SeasonName;
  }

  public String getSeasonDesc() {
    return SeasonDesc;
  }

  @Override
  public String toString() {
    return "Season{" +
        "SeasonName='" + SeasonName + '\'' +
        ", SeasonDesc='" + SeasonDesc + '\'' +
        '}';
  }
}

public class enumClassDemo {
  public static void main(String[] args) {
    Season spring = Season.SPRING;
    System.out.println(spring);
  }
}
