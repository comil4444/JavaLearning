package com.cn.io.client;

import com.cn.io.Constants;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    private int port;


    public NIOClient(int port){
        this.port = port;
    }

    public void start(){
        try (SocketChannel socketChannel = SocketChannel.open()) {

            socketChannel.connect(new InetSocketAddress(this.port));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int sendCount = 0;

        while (sendCount < 10) {
            //write
            byteBuffer.clear();
            byteBuffer.put(("current time : " + System.currentTimeMillis()).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();

            //read
            int readLenth = socketChannel.read(byteBuffer);
            byteBuffer.flip();
            byte[] bytes = new byte[readLenth];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes, "UTF-8"));
            byteBuffer.clear();


            sendCount++;
            Thread.sleep(1000);
        }
    }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new NIOClient(Constants.PORT).start();
    }

}
