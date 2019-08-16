package com.cn.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

    public void connect(int port, String host) throws  Exception {
        //配置客户端线程组
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeClientHandler()) ;
            }
        });

        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        new TimeClient().connect(8080, "localhost");
    }

}
