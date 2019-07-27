package com.cn.io.server;

import com.cn.io.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private int port;
    private Selector selector;
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NIOServer(int port) throws IOException {

        this.port = port;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置地址
        serverSocketChannel.bind(new InetSocketAddress(this.port));

        serverSocketChannel.configureBlocking(false);
        //服务经理就绪
        selector = Selector.open();
        //准备就绪，准备开门迎客
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    public void listen() throws IOException {

        System.out.println("NIO Server is listening port :" + this.port);

        while(true){
            //开始叫号
            selector.select();

            //获取所有准备好的号
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //迭代所有准备好的号
            while(iterator.hasNext()){
                //每次只能处理一个号，同步
                SelectionKey selectionKey = iterator.next();

                iterator.remove();
                //第一个业务搞定了，再去排队做第二个业务
                if(selectionKey.isAcceptable()){
                    System.out.println("accept!");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                //第二个业务搞定，再去排队第三个业务
                }else if(selectionKey.isReadable()){
                    System.out.println("read!");
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    int len = channel.read(buffer);
                    if(len > 0){
                        buffer.flip();
                        String data = new String(buffer.array(),0 ,len);
                        System.out.println("content:\t" + data);
                        channel.register(selectionKey.selector(), SelectionKey.OP_WRITE, data);
                    }else{
                        channel.close();
                    }
                    buffer.clear();

                //第三个业务结束，可以走了。
                }else if(selectionKey.isWritable()){
                    System.out.println("write!");
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    String data = (String) selectionKey.attachment();
                    channel.write(ByteBuffer.wrap(("output data:\t" + data).getBytes()));
                    channel.register(selectionKey.selector(), SelectionKey.OP_READ);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new NIOServer(Constants.PORT).listen();
    }

}
