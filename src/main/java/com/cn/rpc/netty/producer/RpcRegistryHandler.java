package com.cn.rpc.netty.producer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcRegistryHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> serviceMap = new ConcurrentHashMap<String, Object>();
    private List<String> classes = new ArrayList<>();

    public RpcRegistryHandler() {
        scanServiceClass("com.cn.rpc.netty.producer");
        doRegister();
    }

    private void doRegister() {
        for (String clazzStr: classes) {
            try {
                Class clazz = Class.forName(clazzStr);
                Class interf = clazz.getInterfaces()[0];
                serviceMap.put(interf.getName(), clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void scanServiceClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File file = new File(url.getFile());

        for(File f: file.listFiles()) {
            if(file.isDirectory()) {
                scanServiceClass(packageName + "." + f.getName());
            } else {
                classes.add(packageName + "." + f.getName().replace(".class",""));
            }
        }
    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InvokeProtocol request = (InvokeProtocol)msg;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
