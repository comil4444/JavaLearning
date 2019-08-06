package com.cn.io.server.tomcat.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyRequest{

    private InputStream inputStream;
    private String url;
    private String method;

    public MyRequest(InputStream in) throws IOException {
        this.inputStream = in;
        BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));

        String firstLine = br.readLine();
        String[] firstLineInfos = firstLine.split(" ");

        this.url = firstLineInfos[1];
        this.method = firstLineInfos[0];

    }

    public String getUrl(){
        return this.url;
    }

    public String getMethod(){
        return this.method;
    }

}
