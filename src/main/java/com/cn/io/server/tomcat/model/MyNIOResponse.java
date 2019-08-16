package com.cn.io.server.tomcat.model;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyNIOResponse extends MyResponse{

    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public MyNIOResponse(ChannelHandlerContext ctx, HttpRequest req) throws IOException {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(byte[] data) throws IOException {
        ctx.write(new String(data));
    }
}
