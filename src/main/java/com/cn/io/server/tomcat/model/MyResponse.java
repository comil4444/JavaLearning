package com.cn.io.server.tomcat.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(byte[] data) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.outputStream));
        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("Content-Type: text/html\r\n");
        bw.write("\r\n");
        bw.write(new String(data));
        bw.flush();
        bw.close();
    }
}
