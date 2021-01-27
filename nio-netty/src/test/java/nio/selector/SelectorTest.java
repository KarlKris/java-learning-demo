package nio.selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2020/11/4 16:23
 */
public class SelectorTest {


    /**
     * Selector.select()方法是阻塞的
     **/
    @Test
    public void testBlockSelect() throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        System.out.println("1");
        socketChannel.bind(new InetSocketAddress("localhost", 8888));
        System.out.println("2");
        socketChannel.configureBlocking(false);
        System.out.println("3");
        Selector selector = Selector.open();
        System.out.println("4");
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("5");
        /** 证明select()方法是阻塞的 **/
        int select = selector.select();
        System.out.println("6 keyCount=" + select);
        socketChannel.close();
        System.out.println("7 end!");

    }

    /**
     * 如果两个不同的通道注册到用一个选择器里，那么极易出现重复消费问题
     *
     * 原因：因为两个通道接收到事件后会放置到selectionKeys中，消费了事件后却没有把SelectionKey移除
     * 解决方法:iterator.remove();移除消费完的SelectionKey
     **/
    @Test
    public void testRepeatConsume() throws IOException {
        ServerSocketChannel one = ServerSocketChannel.open();
        one.bind(new InetSocketAddress("localhost", 7777));
        one.configureBlocking(false);

        ServerSocketChannel two = ServerSocketChannel.open();
        two.bind(new InetSocketAddress("localhost", 8888));
        two.configureBlocking(false);

        Selector selector = Selector.open();

        SelectionKey oneSelectionKey = one.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey twoSelectionKey = two.register(selector, SelectionKey.OP_ACCEPT);

        boolean run = true;
        while (run) {
            System.out.println("before select()时间:" + System.currentTimeMillis());
            int keyCount = selector.selectNow();
            System.out.println("after select()时间:" + System.currentTimeMillis());
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("keyCount=" + keyCount);
            System.out.println("keys.size()=" + keys.size());
            System.out.println("selectionKeys.size()=" + selectionKeys.size());

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 消费事件
                ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                SocketChannel socketChannel = channel.accept();
                if (socketChannel == null) {
                    System.out.println("出现SocketChannel==null");
                }

                InetSocketAddress localAddress = (InetSocketAddress)channel.getLocalAddress();
                System.out.println("端口号:" + localAddress.getPort() + " 被客户端连接");
                System.out.println("-------------------------------------------------");
                // 移除SelectionKey
                iterator.remove();
            }
            System.out.println("===============================================================");

        }
    }


    /** selector.wakeup()会立即唤醒阻塞在selector.select()或selector.select(timeout)的线程，
     * 即select方法会立即返回值，如果当前没有阻塞，则下一次调用会立即返回
     *
     * 官方解释：wakeup()方法的作用是使尚未返回的第一个选择操作立即返回；
     *          如果当前尚未进行选择操作，那么在没有同时调用selectNow()的情况下，下一次选择操作会立即返回
     *          两个连续的选择操作之间多次调用此方法与只调用一次的效果相同
     * **/
    @Test
    public void testWakeup() throws IOException {
        ServerSocketChannel one = ServerSocketChannel.open();
        one.bind(new InetSocketAddress("localhost", 7777));
        one.configureBlocking(false);

        Selector selector = Selector.open();
        one.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(()->{
            try {
                Thread.sleep(3000);
                // wakeup()
                selector.wakeup();
                System.out.println("Thread:执行selector.wakeup()方法");
                Set<SelectionKey> keys = selector.keys();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("Thread:keys.size():" + keys.size());
                System.out.println("Thread:selectionKeys.size():" + selectionKeys.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        int keyCount = selector.select();
        System.out.println("Main:keyCount:" + keyCount);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        System.out.println("Main:selectionKeys.size():" + selectionKeys.size());
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey next = iterator.next();
            if (next.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel)next.channel();
                SocketChannel socketChannel = channel.accept();
                socketChannel.close();
            }
            iterator.remove();
        }
    }


    /** 测试线程中断阻塞在select()或select(timeout)方法的线程  **/
    @Test
    public void testInterruptInSelect() throws IOException {
        Thread mainThread = Thread.currentThread();

        ServerSocketChannel one = ServerSocketChannel.open();
        one.bind(new InetSocketAddress("localhost", 7777));
        one.configureBlocking(false);

        Selector selector = Selector.open();

        one.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(()->{
            try {
                Thread.sleep(3000);
                mainThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            int keyCount = selector.select();
            if (mainThread.isInterrupted()) {
                break;
            }
            System.out.println("Main:keyCount:" + keyCount);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("Main:selectionKeys.size():" + selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)next.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.close();
                }
                iterator.remove();
            }
        }

        System.out.println("Main Thread End!!!");
    }


    @Test
    public void client1Connect() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        socket.close();
    }

    @Test
    public void client2Connect() throws IOException {
        Socket socket = new Socket("localhost", 7777);
        socket.close();
    }


}
