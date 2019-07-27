package com.cn.io.client;

import com.cn.io.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class BIOClient {

    private int port;
    private String ip;

    public BIOClient(int port, String ip){
        this.port = port;
        this.ip = ip;
    }

    public void connect() throws IOException {
        Socket socket = new Socket(this.ip , this.port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hi Eric!".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        new BIOClient(Constants.PORT, Constants.IP).connect();
    }
}
