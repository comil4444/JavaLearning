package com.cn.io.server.tomcat.model;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyNIORequest extends MyRequest{

    private ChannelHandlerContext ctx;
    private HttpRequest req;

    private String url;
    private String method;

    public MyNIORequest(ChannelHandlerContext ctx, HttpRequest req) throws IOException {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl(){
        return this.req.uri();
    }

    public String getMethod(){
        return this.req.method().toString();
    }

}
