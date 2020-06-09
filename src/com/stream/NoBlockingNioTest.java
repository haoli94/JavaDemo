package com.stream;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

public class NoBlockingNioTest {
  //    读数据状态--完全准备就绪
//    选择器：Selector
//
//===============================
//    一、使用NIO完成网络通信的三个核心
//1、通道（Channel:：负责连接
//    java.nio.channels.Channel
//                                  |--SelectableChannel
//                                  |--ServerSocketChannel
//                                  |--DatagramChannel
//
//                                  |--Pipe.SinkChannel
//                                  |--Pipe.SourceChannel
//
//    FileChannel不能切换成非阻塞模式
//
//
//2、缓冲区（Buffer）：负责数据的存取
//
//3、选择器（Selector）：是SelectableChannel的多路复涌去。用于监控SelectableChannel的IO情况
//
//
// https://blog.csdn.net/fuyuwei2015/article/details/73748390


  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("请输入一个字符串(中间能加空格或符号)");
    String line = input.nextLine();
    String s = LocalDateTime.now().toString() + "\n" + line;
    System.out.println(s);
    long s1 = System.currentTimeMillis();

    //todo 为何两个线程启动 不是串行的

    new Thread(() -> {
      new NoBlockingNioTest().server();
    }).start();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(() -> {
      new NoBlockingNioTest().client();
    }).start();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(() -> {
      new NoBlockingNioTest().client();
    }).start();

    System.out.println("END=" + (System.currentTimeMillis() - s1));
  }


  //客户端
  public void client() {
    SocketChannel socketChannel = null;
    try {

      //1、获取通道
      socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

      //2、切换成非阻塞模式
      socketChannel.configureBlocking(false);

      //3、分配缓冲区
      ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

      //测试中是发送一个 时间字符串给server

      String msg = getMsg();
      buffer.put(msg.getBytes());
      buffer.flip();

      //4、发送数据
      socketChannel.write(buffer);
      buffer.clear();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      if (socketChannel != null) {
        try {
          socketChannel.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private String getMsg() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入：");
//        while (scanner.hasNext()) {
//            String msg = scanner.next();
//        }
//        return "null";

//        Scanner input = new Scanner(System.in);
//        System.out.println("请输入一个字符串(中间能加空格或符号)");
//        String line = input.nextLine();
//        String s = LocalDateTime.now().toString() + "\n" + line;
//        System.out.println(s);
    // scanner 在junit中不能正常工作
    return "1111111";

  }

//        System.out.println("请输入一个字符串(中间不能加空格或符号)");
//        String b = input.next();
//        System.out.println("请输入一个整数");
//        int c;
//        c = input.nextInt();
//        System.out.println("请输入一个double类型的小数");
//        double d = input.nextDouble();
//        System.out.println("请输入一个float类型的小数");
//        float f = input.nextFloat();
//        System.out.println("按顺序输出abcdf的值：");
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(f);


  //服务器--阻塞式
  public void server() {
    ServerSocketChannel ssChannel = null;
    int port = 9898;
    try {
      //1、获取通道 Allocate an unbound server socket channel
      ssChannel = ServerSocketChannel.open();
      //2、设置非阻塞 Set nonblocking mode for the listening socket
      ssChannel.configureBlocking(false);
      //3、绑定连接
      ssChannel.bind(new InetSocketAddress(port));
      System.out.println("Listening on port " + port);

      //4、获取选择器 Create a new Selector for use below
      Selector selector = Selector.open();

      //5、将通道注册到选择器,并且指定“监听事件”  ，对比之前的 accept方法=阻塞 Register the ServerSocketChannel with the Selector
      ssChannel.register(selector, SelectionKey.OP_ACCEPT);

      //6、轮询式的获取选择器上“已经准备就绪”的事件
      while (true) {
        // This may block for a long time. Upon returning, the
        // selected set contains keys of the ready channels.
        int n = selector.select();
        if (n == 0) {
          continue; // nothing to do
        }
        //至少有一个准备就绪的事件

        //7、包括所有注册的“选择键（已就绪的监听事件）” Get an iterator over the set of selected keys
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

        //8、获取准备就绪的事件  Look at each key in the selected set
        while (iterator.hasNext()) {
          //9、判断具体是什么准备就绪事件
          SelectionKey sk = iterator.next();
          if (sk.isAcceptable()) { // Is a new connection coming in?
            //10、若"接受就绪"，获取客户端连接
            SocketChannel sChannel = ssChannel.accept();
            //11、设置非阻塞模式
            sChannel.configureBlocking(false);
//            try {
//              sayHello(sChannel);
//            } catch (Exception e) {
//              e.printStackTrace();
//            }
            //12、将该通道注册到选择器上
            sChannel.register(selector, SelectionKey.OP_READ);
//            ServerSocketChannel server = (ServerSocketChannel) key.channel();
//            SocketChannel channel = server.accept();
//            registerChannel(selector, channel, SelectionKey.OP_READ);
          } else if (sk.isReadable()) {
            //13、获取当前选择器上“读就绪”状态的通道 Is there data to read on this channel?
            SocketChannel sChannel = (SocketChannel) sk.channel();
            //14、读取数据
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear(); // Empty buffer
            int len = 0;
            while ((len = sChannel.read(buffer)) > 0) {
              buffer.flip();
              byte[] bf =  new byte[len];
              buffer.get(bf);
//              System.out.println(new String(buffer.array(), 0, len));
              System.out.println(new String(bf));
              buffer.clear();
            }
            if (len < 0) {
              // Close channel on EOF, invalidates the key
              sChannel.close();
            }
          }
          //15、取消选择键 Remove key from selected set; it's been handled
          iterator.remove();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (ssChannel != null) {
        try {
          ssChannel.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Register the given channel with the given selector for the given
   * operations of interest
   */

  protected void registerChannel(Selector selector,
                                 SelectableChannel channel, int ops) throws Exception {
    if (channel == null) {
      return; // could happen
    }
    // Set the new channel nonblocking
    channel.configureBlocking(false);
    // Register it with the selector
    channel.register(selector, ops);
  }

  // 分配指定大小的缓冲区
//  private ByteBuffer buffer = ByteBuffer.allocate(1024);
   private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

//  private void sayHello(SocketChannel channel) throws Exception {
//    buffer.clear();
//    buffer.put("Hi there!\r\n".getBytes());
//    buffer.flip();
//    channel.write(buffer);
//  }


}
