package com.cn.io.client;

import com.cn.io.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient2 {

    private Selector clientSelector;
    private SocketChannel clientChannel;

    public NIOClient2(String ip, int port){
        init(ip, port);
    }

    private void init(String ip, int port){
        try {
            clientSelector = Selector.open();
            clientChannel = SocketChannel.open();
            clientChannel.configureBlocking(false);
            clientChannel.connect(new InetSocketAddress(ip, port));
            clientChannel.register(clientSelector, SelectionKey.OP_READ);
            while (!clientChannel.finishConnect()){

            }
            System.out.println("已连接！");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void start() throws IOException {
        //负责读取的线程
        new Chat(this.clientSelector).start();
        //负责写的线程
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        while(true){
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if(data.equals("bye")){
                clientChannel.close();
                clientSelector.close();
                return;
            }
            byteBuffer.put(data.getBytes());
            byteBuffer.flip();
            this.clientChannel.write(byteBuffer);
            byteBuffer.clear();

        }

    }


    public static void main(String[] args) throws IOException {
        new NIOClient2(Constants.IP, Constants.PORT).start();
    }

    static class Chat extends Thread{
        private Selector selector;

        public Chat(Selector selector){
            this.selector = selector;
        }

        @Override
        public void run(){
            try {
                while (true){
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = keys.iterator();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    while (keyIterator.hasNext()){
                        SelectionKey selectionKey = keyIterator.next();
                        if (selectionKey.isValid()){
                            if (selectionKey.isReadable()){
                                SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                                socketChannel.read(byteBuffer);
                                byteBuffer.flip();
                                byte[] bytes = new byte[byteBuffer.remaining()];
                                byteBuffer.get(bytes);
                                System.out.println(new String(bytes));
                                byteBuffer.clear();
                            }
                        }
                    }
                }
            }catch (ClosedSelectorException ce){
                return;
            }
            catch (Exception e){
                e.printStackTrace();

            }
        }
    }

}
