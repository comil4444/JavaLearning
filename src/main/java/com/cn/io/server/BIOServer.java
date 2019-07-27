package com.cn.io.server;


import com.cn.io.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    private int port;

    public BIOServer(int port){
        this.port = port;
    }

    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        while(true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] temp = new byte[1024];
            int len = 0;
            while((len = inputStream.read(temp))!=-1){
                System.out.println(new String(temp, 0 , len));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(Constants.PORT).listen();
    }
}
