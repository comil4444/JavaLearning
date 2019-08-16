package com.cn.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

    public void bind(int port) throws Exception {

        //配置服务段的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());

        //绑定端口，同步等待成功
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

        //等待服务端监听端口关闭
        channelFuture.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        worker.shutdownGracefully();
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        new TimeServer().bind(8080);
    }
}
