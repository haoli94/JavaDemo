package com.memory;

public class MemoryDemo {
  // https://zhuanlan.zhihu.com/p/100014103 NIO/ BIO
  public static void main(String[] args) {
    // 最大堆内存
    System.out.println("max memory: " + (Runtime.getRuntime().maxMemory()) / 1024L / 1024L + "MB");
    // 初始堆内存
    System.out.println("total memory: " + (Runtime.getRuntime().totalMemory()) / 1024L / 1024L + "MB");
    // 已经拿到的堆内存中剩余内存
    System.out.println("free memory: " + (Runtime.getRuntime().freeMemory()) / 1024L / 1024L + "MB");
    // usedMemory = totalMemory - freeMemory
  }
}
